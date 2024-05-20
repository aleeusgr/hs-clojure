; test/hs_clojure/db_test.clj
(ns hs-clojure.db-test
  (:require [clojure.test :refer :all]
            [hs-clojure.db :as db]
            [hs-clojure.db.patients :as db-patients]))

(use-fixtures :each (fn [f] (db-patients/create-patients-table db/spec) (f) (db-patients/drop-patients-table db/spec)))

(def patients [{:name "John Doe" :sex "Male" :date-of-birth "1990-01-01" :address "123 Main St" :social-security-number "123-45-6789"}
             {:name "Jane Doe" :sex "Female" :date-of-birth "1990-02-01" :address "456 Elm St" :social-security-number "987-65-4321"}])

(deftest database-schema-test
  (testing "Database schema creation"
    (let [result (db-patients/create-patients-table db/spec)]
      (is (not (empty? result))))))

(deftest insert-patient-test
  (testing "Insert patient"
    (let [patient (patients 0)
          id (db-patients/insert-patient db/spec
                                         {:name (:name patient)
                                          :sex (:sex patient)
                                          :date_of_birth (java.sql.Date/valueOf (:date-of-birth patient))
                                          :address (:address patient)
                                          :social_security_number (:social-security-number patient)})]
      (is (> id 0)))))

