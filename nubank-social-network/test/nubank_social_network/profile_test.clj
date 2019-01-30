(ns nubank-social-network.profile-test
  (:require [clojure.test :refer :all]
            [nubank-social-network.profile :as profile]
            [nubank-social-network.profile-persistence :as pdb]))

(deftest add-new-profile
  (testing "Create a new profile"
    (pdb/atom-remove-all)
    (profile/save pdb/in-memory-storage-profile "Profile1" "1990-01-01" "male")

    (is (= 1 (count (profile/get-all pdb/in-memory-storage-profile))))))

(deftest not-add-same-profile
  (testing "Don't add the same profile, with the same name"
    (pdb/atom-remove-all)
    (profile/save pdb/in-memory-storage-profile "Profile1" "1980-01-01" "male")
    (profile/save pdb/in-memory-storage-profile "Profile1" "1990-01-01" "female")

    (println (profile/get-all pdb/in-memory-storage-profile))
    (is (= 1 (count (profile/get-all pdb/in-memory-storage-profile))))))

(deftest connect-two-profile
  (testing "Connect two profiles like friendly"
    (pdb/atom-remove-all)
    (let [profile-connected {:id 1
                    :name "Profile1"
                    :genre "male"
                    :birthdate "1981-01-01"
                    :follow #{2}}]

      (is (contains? (get profile-connected :follow) 2)))))