(ns hackynews.impl
  (:require [hackynews.domain :as domain]
            [clojure.spec.alpha :as s]
            [clj-http.client :as http]
            [failjure.core :as f]
            [clojure.xml :as xml]
            [cheshire.core :refer [parse-string]]))

(comment
  (require '[clojure.spec.test.alpha :as stest])
  (stest/instrument)
)

(defn- parse-item [item-node]
  (reduce (fn [item node]
            (case (:tag node)
              :title (assoc item :title (-> node :content first))
              :description (assoc item :description (-> node :content first))
              :link (assoc item :link-uri (-> node :content first (java.net.URI.)))
              :comments (assoc item :comments-uri (-> node :content first (java.net.URI.)))
              :pubDate (assoc item :pub-date (-> node :content first))
              )
            ) {} (:content item-node)))

(defn- parse-channel [channel-node]
  (reduce (fn [feed node]
            (case (:tag node)
              :title (assoc feed :title (-> node :content first))
              :description (assoc feed :description (-> node :content first))
              :link (assoc feed :link-uri (-> node :content first (java.net.URI.)))
              :item (update feed :items conj (parse-item node))
              feed)) {} (:content channel-node)))

(defn get-rss-feed [uri]
  (f/attempt-all [feed (f/try* (xml/parse (str uri)))
                  channel (-> feed :content first)]
                 (parse-channel channel)))

(s/def get-rss-feed ::domain/get-rss-feed)

(defn- fetch-item-content [item]
  (f/attempt-all
    [req {:query-params {:url (str (:link-uri item))}
          :headers {"x-api-key" "APZ0tkhWJ9nGGBfKnejQjzjGHJ2pvVZzLtHMfB4r"}}
     resp (f/try* (http/get "https://mercury.postlight.com/parser" req))
     content (-> resp
                 (:body)
                 (parse-string true)
                 (:content))]
    (assoc item :content content)
    (f/when-failed [e] item)))

(defn try-fetch-item-content [blacklist item]
  (if (some #(re-matches % (:link-uri item)) blacklist)
    item
    (fetch-item-content item)))

(s/def try-fetch-item-content ::domain/try-fetch-item-content)

(defn try-fetch-items [blacklist feed]
  (map #(try-fetch-item-content blacklist %) (:items feed)))

(s/def try-fetch-items ::domain/try-fetch-items)


(defn fetch-rss-feed-items [blacklist uri]
  (f/attempt-all
    [feed (get-rss-feed uri)]
    (try-fetch-items blacklist feed)))

(s/def fetch-rss-feed-items ::domain/fetch-rss-feed-items)

(defn generated-result [name birthdate genre]
  (f/attempt-all
    (println "ESTOU NO METODO generated-result")
    {:id 1
    :name name
    :birthdate birthdate
    :genre genre}))

(s/def generated-result ::domain/generated-result)

(defn create-profile [name birthdate genre]
  (generated-result name birthdate genre))

(s/def create-profile ::domain/create-profile)

