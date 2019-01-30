(ns hackynews.domain
  (:require [clojure.spec.alpha :as s]
            [failjure.core :as f]))

(s/def ::rss-feed
  (s/keys
    :req-un [::title ::description ::link-uri ::items]))

(s/def ::feed-item
  (s/keys
    :req-un [::title ::description ::link-uri ::comments-uri ::pub-date]))

(s/def ::title string?)
(s/def ::description string?)
(s/def ::link-uri uri?)
(s/def ::comments-uri uri?)

(s/def ::items (s/coll-of ::feed-item))

(s/def ::blacklist (s/coll-of #(instance? java.util.regex.Pattern %)))

(s/def ::content string?)
(s/def ::feed-item-with-content
  (s/and ::feed-item (s/keys :req-un [::content])))

(s/def ::fetched-item-result
  (s/or
    :ok ::feed-item-with-content
    :error ::feed-item))

(s/def ::try-fetch-item-content
  (s/fspec
    :args (s/cat
            :blacklist ::blacklist
            :item ::feed-item)
    :ret ::fetched-item-result))

(s/def ::try-fetch-items
  (s/fspec
    :args (s/cat
            :blacklist ::blacklist
            :feed ::rss-feed)
    :ret (s/coll-of ::fetched-item-result)))

(s/def ::get-rss-feed
  (s/fspec
    :args (s/cat :uri uri?)
    :ret (s/or
           :ok ::rss-feed
           :error f/failed?)))

(s/def ::fetch-rss-feed-items
  (s/fspec
    :args (s/cat
            :blacklist ::blacklist
            :uri ::link-uri)
    :ret (s/or
           :ok (s/coll-of ::fetched-item-result)
           :error f/failed?)))

;;Criar definição dos tipos de dados para os atributos
(s/def ::profile
  (s/keys
    :req-un [::name ::birthdate ::genre]))

(s/def ::profile-result
  (s/keys
    :req-un [::id ::name ::birthdate ::genre]))

(s/def ::id number?)
(s/def ::name string?)
(s/def ::birthdate string?)
(s/def ::genre string?)

(s/def ::create-profile
  (s/fspec
    :args (s/cat
            :name string?
            :birthdate string?
            :genre string?)
    :ret (s/or
           :ok ::profile-result
           :error f/failed?)))

(s/def ::generated-result
  (s/fspec
    :args (s/cat
            :name string?
            :birthdate string?
            :genre string?)
    :ret (s/or
           :ok ::profile-result
           :error f/failed?)))
;;fim da definição