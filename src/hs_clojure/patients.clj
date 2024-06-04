(ns hs-clojure.patients
  (:require [hs-clojure.db.patients :as db-patients]
            [hs-clojure.db :as db]
            [clojure.java.jdbc :as jdbc])
  (:import (java.sql Date)))

(defn validate-inputs
  [name sex date-of-birth address social-security-number]
  (let [name-pattern #"[A-Za-z]+ [A-Za-z]+"]
    (if (re-matches name-pattern name)
      true
      (throw (Exception. "Name must be two words containing only letters")))))

(defn add-patient
  [name sex date-of-birth address social-security-number]
  (if (validate-inputs name sex date-of-birth address social-security-number)
    (let [date-of-birth (java.sql.Date/valueOf date-of-birth)]
      (db-patients/insert-patient db/spec
                                   {:name name
                                    :sex sex
                                    :date_of_birth date-of-birth
                                    :address address
                                    :social_security_number social-security-number}))
    (throw (Exception. "Invalid input"))))

(defn get-patient-by-id
  [spec id]
  (let [id-pattern #"\d+" ; only allow digits
        id-str (str id)] ; convert id to a string
    (if (re-matches id-pattern id-str)
      (first (jdbc/query spec
                         ["SELECT * FROM patients WHERE id = ?" id]))
      (throw (Exception. "Invalid patient ID")))))

(defn get-all-patients
  ([]
   (get-all-patients ["*"]))
  ([cols]
   (db-patients/get-all-patients db/spec {:cols (map name cols)})))


(defn update-patient
  [id name sex date-of-birth address social-security-number]
  (let [id (Long/parseLong id) ; convert id to a Long
        patient (get-patient-by-id db/spec id)]
    (if patient
      (let [date-of-birth (java.sql.Date/valueOf date-of-birth)]
        (db-patients/update-patient db/spec
                                   {:id id
                                    :name name
                                    :sex sex
                                    :date_of_birth date-of-birth
                                    :address address
                                    :social_security_number social-security-number}))
      (throw (Exception. "Patient not found")))))

(defn delete-patient
  [id]
  (let [patient (get-patient-by-id db/spec id)]
    (if patient
      (db-patients/delete-patient db/spec {:id id})
      (throw (Exception. "Patient not found")))))

(defn get-patient-by-social-security-number
  [spec social-security-number]
  (first (jdbc/query spec
                      ["SELECT * FROM patients WHERE social_security_number = ?" social-security-number])))
