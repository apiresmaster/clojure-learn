(ns hackynews.domain-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [hackynews.domain :as domain]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.test.alpha :as stest]))

(deftest valid-profile-map
  (testing "Valid the profile map attributes."
    (is (s/valid? ::domain/profile {:name "NomeTeste"
                                  :nickname "nometeste"
                                  :birthdate "1980-01-01"
                                  :genre "male"}))))