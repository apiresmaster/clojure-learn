(ns nubank-social-network.profile-persistence
  (:require [nubank-social-network.profile :as profile]))

;Define an atom for storage profiles in-memory
;to do - how to limit direct access this variable out of namespace
(def ^{:private true} atom-db-profiles (atom #{ }))

(defn atom-remove-all
  []
  (reset! atom-db-profiles #{ }))

(defn- generate-id
  "Define a valid ID to persist the profile"
  [profile]
  (if (= 0 (count @atom-db-profiles))
    1
    (inc (reduce max (map #(:id %) @atom-db-profiles)))))

(defn- find-all-profile
  [fn-find]
  (filter fn-find @atom-db-profiles))

(def in-memory-storage-profile
  (reify profile/IProfile
    (save [this name birthdate genre]
      (let [map-profile-data {:name name
                              :birthdate birthdate
                              :genre genre}]

        (when (zero? (count (find-all-profile #(= (:name %) name))))
          (swap! atom-db-profiles conj (assoc map-profile-data :id (generate-id map-profile-data))))))

    (get-by-name [this name]
      (find-all-profile #(= (:name %) name)))

    (get-all [this]
      @atom-db-profiles)))