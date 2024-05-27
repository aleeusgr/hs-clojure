; src/hs_clojure/routes.clj
(ns hs-clojure.routes
  (:require [compojure.core :refer :all]
            [hs-clojure.patients :as patients]))

(defroutes patient-routes
  (POST "/patients" [name sex date-of-birth address social-security-number]
    (patients/add-patient name sex date-of-birth address social-security-number)
    {:status 201})
  ; Add more routes here
  )

(def app
  (-> patient-routes
      ; Add middleware here
      ))
