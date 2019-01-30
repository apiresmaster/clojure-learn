(ns nubank-robots.model.robot-test
  (:require [clojure.test :refer :all]
            [nubank-robots.robot :refer :all]))

(deftest create-robot-certain-position
  (testing "Create a robot in a certain position"
    (is (= 0 1))))