; src/hs_clojure/patients.clj
(ns hs-clojure.patients
  (:require [hs-clojure.db.patients :as db-patients]
            [hs-clojure.db :as db])
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

(defn get-patient
  [name]
  (db-patients/get-patient-by-name-like db/spec {:name-like (str "%" name "%")}))

(defn get-patient-by-id
  [id]
  (db-patients/get-patient-by-id db/spec {:id id}))

(defn get-all-patients
  ([]
   (get-all-patients ["*"]))
  ([cols]
   (db-patients/get-all-patients db/spec {:cols (map name cols)})))

(defn update-patient
  [id name sex date-of-birth address social-security-number]
  (db-patients/update-patient db/spec
                              {:id id
                               :name name
                               :sex sex
                               :date-of-birth date-of-birth
                               :address address
                               :social-security_number social-security-number}))

(defn delete-patient
  [id]
  (db-patients/delete-patient db/spec {:id id}))
