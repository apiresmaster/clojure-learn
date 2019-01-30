(ns nubank-robots.model.simulation-space-test
  (:require [clojure.test :refer :all]
            [nubank-robots.model.simulation-space :as simulation]))

(defn simulation-create-line
  [result num-lin num-col]
  (if (> num-col 50)
    result
    (recur (conj result {:lin num-lin
                         :col num-col}) num-lin (inc num-col))))

(deftest create-empty-simulation-with-50x50
  (testing "Create an empty simulation space with 50x50"
    (def empty-simulation (fn [lin col](repeat 50 {:lin (inc lin) :col (inc col)})))
    (is (= {:lin 1
            :col 1} ))))


