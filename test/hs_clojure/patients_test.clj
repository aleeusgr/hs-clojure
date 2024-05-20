; test/hs_clojure/test_patients.clj
(ns hs-clojure.patients-test
  (:require [clojure.test :refer :all]
            [hs-clojure.patients :refer :all]
            [hs-clojure.db :as db]
            [hs-clojure.db.patients :as db-patients]
            [hs-clojure.test-helpers :refer [clean-database]]
            [clojure.set :as set]
            [clojure.string :as str]))

(use-fixtures :each clean-database)

(def patients
  [{:name "John Doe" :sex "Male" :date-of-birth "1990-01-01" :address "123 Main St" :social-security-number "123-45-6789"}
   {:name "Jane Doe" :sex "Female" :date-of-birth "1990-02-01" :address "456 Elm St" :social-security-number "987-65-4321"}])

(deftest patient-operations-test
  (testing "Happy path: add patient"
    (let [patient (patients 0)
          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
      (let [retrieved-patient (get-patient-by-id db/spec id)
            retrieved-date-of-birth (-> retrieved-patient :date_of_birth .toString)]
        (is (= (:name patient) (:name retrieved-patient))
            (is (= (:sex patient) (:sex retrieved-patient))
            (is (= (:address patient) (:address retrieved-patient)))))

  (testing "Error handling: invalid input"
    (is (thrown? Exception (add-patient "John Doe" "Male" "1990-01-01" "123 Main St")))))))

(deftest get-patient-by-id-test
  (testing "Happy path: get patient by ID"
    (let [patient (patients 0)
          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
      (let [retrieved-patient (get-patient-by-id db/spec id)
            retrieved-patient (update retrieved-patient :date_of_birth str)
            patient (dissoc patient :date-of-birth :social-security-number)]
        (is (= patient (select-keys retrieved-patient (keys patient)))))))

  (testing "Error handling: patient not found"
    (is (nil? (get-patient-by-id db/spec 99999)))))

(deftest delete-patient-test
  (testing "Happy path: delete patient"
    (let [patient (patients 0)
          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
      (delete-patient id)
      (is (nil? (get-patient-by-id db/spec id)))))

  (testing "Error handling: patient not found"
    (is (thrown? Exception (delete-patient 99999)))))

(deftest update-patient-test
  (testing "Happy path: update patient"
    (let [patient (patients 0)
          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))
          updated-patient (patients 1)
          updated-patient-id (update-patient id (:name updated-patient) (:sex updated-patient) (:date-of-birth updated-patient) (:address updated-patient) (:social-security-number updated-patient))]
      (let [retrieved-patient (get-patient-by-id db/spec updated-patient-id)
            retrieved-patient (update retrieved-patient :date_of_birth str)
            patient (dissoc updated-patient :date-of-birth :social-security-number)]
        (is (= patient (select-keys retrieved-patient (keys patient)))))))

  (testing "Error handling: patient not found"
    (is (thrown? Exception (update-patient 99999 "Jane Doe" "Female" "1990-02-01" "456 Elm St" "987-65-4321"))))))
