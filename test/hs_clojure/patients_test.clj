(ns hs-clojure.patients-test
  (:require [clojure.test :refer :all]
            [hs-clojure.patients :refer :all]
            [hs-clojure.db :as db]))

(use-fixtures :once (fn [f]
                      (db/create-patients-table)
                      (f)))
(deftest test-add-patient
  (testing "Add patient"
    (let [patient {:name "John Doe" :sex "M" :date-of-birth "1990-01-01" :address "123 Main St" :social-security-number "123-45-6789"}
          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
      (is (pos? id))
      (let [added-patient (get-patient-by-id db/spec 1)]
        (println "Added patient:" added-patient) ; print the added patient to see what's going on
        (is (some? added-patient)) ; check if the added patient is not nil
        (when added-patient
          (is (= (:name patient) (:name added-patient)))
          (is (= (:sex patient) (:sex added-patient)))
          (is (= (:address patient) (:address added-patient))))))))
