(ns hs-clojure.patients
  (:require [hs-clojure.db.patients :as db-patients]
            [hs-clojure.db :as db]
            [clojure.java.jdbc :as jdbc])
  (:import (java.sql Date)))

(defn add-patient
  [name sex date-of-birth address social-security-number]
  (let [date-of-birth (java.sql.Date/valueOf date-of-birth)]
    (db-patients/insert-patient db/spec
                                {:name name
                                 :sex sex
                                 :date_of_birth date-of-birth
                                 :address address
                                 :social_security_number social-security-number})))

(defn get-patient-by-id
  [spec id]
  (first (jdbc/query spec
                     ["SELECT * FROM patients WHERE id = ?" id])))

(defn get-all-patients
  ([]
   (get-all-patients ["*"]))
  ([cols]
   (db-patients/get-all-patients db/spec {:cols (map name cols)})))

(defn update-patient
  [id name sex date-of-birth address social-security-number]
  (let [date-of-birth (java.sql.Date/valueOf date-of-birth)]
    (db-patients/update-patient db/spec
                                {:id id
                                 :name name
                                 :sex sex
                                 :date_of_birth date-of-birth
                                 :address address
                                 :social_security_number social-security-number})))

(defn delete-patient
  [id]
  (db-patients/delete-patient db/spec {:id id}))
