(ns hs-clojure.patients-test
  (:require [clojure.test :refer :all]
            [hs-clojure.patients :refer :all]
            [hs-clojure.db :as db]
            [hs-clojure.db.patients :as db-patients]
            [hs-clojure.test-helpers :refer [clean-database]]))

(use-fixtures :each clean-database)

(deftest test-add-patient
  (testing "Add patient"
    (let [patient {:name "John Doe" :sex "M" :date-of-birth "1990-01-01" :address "123 Main St" :social-security-number "123-45-6789"}
          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
      (is (pos? id))
      (let [added-patient (get-patient-by-id db/spec 1)] 
        (println "Added patient:" added-patient) 
        (is (some? added-patient)) 
        (when added-patient
          (is (= (:name patient) (:name added-patient)))
          (is (= (:sex patient) (:sex added-patient)))
          (is (= (:address patient) (:address added-patient))))))))
