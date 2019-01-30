(ns nubank-social-network.profile
  (:require [clojure.spec.test.alpha :as stest]))

(defprotocol IProfile
  (save [this name birthdate genre])
  (get-by-name [this name])
  (get-all [this]))

(defn invoke-connect-profile
  [id target-id]
  ;;todo implement this function with persistence and validation
  )