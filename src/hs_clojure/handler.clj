(ns hs-clojure.handler
  (:require [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hs-clojure.db :as db]
            [hs-clojure.patients :as patients]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/patients" [] (patients/get-all-patients))
  (GET "/patients/:id" [id] (patients/get-patient-by-id db/spec (Integer/parseInt id)))
  (POST "/patients" [name sex date-of-birth address social-security-number]
    (let [date-of-birth (java.sql.Date/valueOf date-of-birth)]
      (patients/add-patient name sex date-of-birth address social-security-number)))
  (PUT "/patients/:id" [id name sex date-of-birth address social-security-number]
    (let [date-of-birth (java.sql.Date/valueOf date-of-birth)]
      (patients/update-patient (Integer/parseInt id) name sex date-of-birth address social-security-number)))
  (DELETE "/patients/:id" [id]
    (patients/delete-patient (Integer/parseInt id)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
