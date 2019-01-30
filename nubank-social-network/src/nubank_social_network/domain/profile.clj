(ns nubank-social-network.domain.profile
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.test.alpha :as stest]
            [clj-time.core :as t]
            [clojure.string :as str]))

(s/def ::id (s/and integer? pos?))
(s/def ::name (s/and string? not-empty #(<= (count %) 30)))
(s/def ::nickname (s/and string? #(and (>= (count %) 6) (<= (count %) 12))))
(s/def ::birthdate string?)
(s/def ::genre string?)
(s/def ::target-id (s/and integer? pos?))
(s/def ::status string?)
(s/def ::error nat-int?)

(s/def ::profile
  (s/keys :req-un [::name ::nickname ::birthdate ::genre]))

(s/def ::profile-result
  (s/and ::profile (s/keys :req-un [::id])))

(s/def ::profile-connect
  (s/keys
    :req-un [::id ::target-id]))

(s/def ::profile-connect-result
  (s/keys
    :req-un [::target-id ::status]))

(s/def ::profile-connected (s/or :ok (s/keys :req [::profile-connect-result])
                                 :err (s/keys :req [::error])))

(s/fdef invoke-connect-profile
        :args (s/cat
                :profile-connect ::profile-connect)
        :ret (s/keys :req [::target-id ::status]))

(defn invoke-connect-profile [profile-connect]
  {::target-id 10
   ::status (if (< (:target-id profile-connect) 100)
              (str "Connection error code -100")
              (str "OK"))})