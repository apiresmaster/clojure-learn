(ns hackynews.domain
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]))

(defn invoke-connect-profile
  [id target-id]
  (str "SO DEVOLVO TESTO"))

(s/def ::id integer?)
(s/def ::target-id integer?)

(s/def ::status string?)
(s/def ::connect-error string?)

(s/def ::profile-connect
  (s/keys
    :req-un [::id ::target-id]))

(s/def ::profile-connect-result
  (s/keys
    :req-un [::target-id ::status]))

(s/def ::profile-connected (s/or :ok (s/keys :req [::profile-connect-result])
                                 :err (s/keys :req [::connect-error])))

(s/fdef invoke-connect-profile
        :args (s/cat
                :profile-connect ::profile-connect)
        :ret ::profile-connected)

;;CONTEUDO DO SITE

(defn invoke-service [service request]
  (str "Ocorreu um erro"))

(defn run-query [service query]
  (let [{::keys [result error]} (invoke-service service {::query query})]
    (or result error)))

(s/def ::query string?)
(s/def ::request (s/keys :req [::query]))
(s/def ::result (s/coll-of string? :gen-max 3))
(s/def ::error int?)
(s/def ::response (s/or :ok (s/keys :req [::result])
                        :err (s/keys :req [::error] :default -1)))

(s/fdef invoke-service
        :args (s/cat
                :service any?
                :request ::request)
        :ret ::response)

(s/fdef run-query
        :args (s/cat
                :service any?
                :query string?)
        :ret (s/or
               :ok ::result
               :err ::error))



