(ns hs-clojure.patients-test
  (:require [hs-clojure.patients :refer :all]
            [clojure.test :refer :all]))

(deftest test-add-patient
  (testing "add-patient"
    (let [patient {:name "John Doe" :sex "M" :date-of-birth "1990-01-01" :address "123 Main St" :social-security-number "123-45-6789"}
          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
      (is (pos? id))
      (is (not (= {:name "John Doe", :sex "M", :date-of-birth "1990-01-01", :address "123 Main St", :social-security-number "123-45-6789"} {:id 1, :name "John Doe", :sex "M", :date_of_birth #inst "1989-12-31T22:00:00.000-00:00", :address "123 Main St", :social_security_number "123-45-6789", :created_at #inst "2024-05-17T05:22:54.389484000-00:00"}))))))

;(deftest test-get-patient
;  (testing "get-patient"
;    (let [patient {:name "Jane Doe" :sex "F" :date-of-birth "1995-02-02" :address "456 Elm St" :social-security-number "987-65-4321"}
;          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
;      (is (= patient (get-patient (:name patient)))))))
;
;(deftest test-get-patient-by-id
;  (testing "get-patient-by-id"
;    (let [patient {:name "Bob Smith" :sex "M" :date-of-birth "1980-03-03" :address "789 Oak St" :social-security-number "555-55-5555"}
;          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
;      (is (= patient (get-patient-by-id id))))))
;
;(deftest test-get-all-patients
;  (testing "get-all-patients"
;    (let [patients [{:name "Alice Johnson" :sex "F" :date-of-birth "1992-04-04" :address "321 Pine St" :social-security-number "111-11-1111"}
;                   {:name "Bob Johnson" :sex "M" :date-of-birth "1990-05-05" :address "901 Maple St" :social-security-number "222-22-2222"}]]
;      (doseq [patient patients]
;        (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient)))
;      (is (= patients (get-all-patients ["*"]))))))
;
;(deftest test-update-patient
;  (testing "update-patient"
;    (let [patient {:name "Charlie Brown" :sex "M" :date-of-birth "1985-06-06" :address "123 Main St" :social-security-number "333-33-3333"}
;          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
;      (update-patient id "Charlie Brown Jr." "M" "1985-06-06" "123 Main St" "333-33-3333")
;      (is (= {:name "Charlie Brown Jr." :sex "M" :date-of-birth "1985-06-06" :address "123 Main St" :social-security-number "333-33-3333"} (get-patient-by-id id))))))
;
;(deftest test-delete-patient
;  (testing "delete-patient"
;    (let [patient {:name "David Lee" :sex "M" :date-of-birth "1990-07-07" :address "456 Elm St" :social-security-number "444-44-4444"}
;          id (add-patient (:name patient) (:sex patient) (:date-of-birth patient) (:address patient) (:social-security-number patient))]
;      (delete-patient id)
;      (is (nil? (get-patient-by-id id))))))
