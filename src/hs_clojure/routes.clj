(ns hs-clojure.routes
  (:require [compojure.core :refer :all]
            [cheshire.core :as json]
            [hs-clojure.patients :as patients]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [hiccup.page :refer [html5]]))

(defn patient-form []
  (let [csrf-token (anti-forgery-field)
        patients (patients/get-all-patients)]
    (html5
      [:head
       [:title "Add a new patient"]]
      [:body
       [:h2 "Add a new patient:"]
       [:form {:action "/patients" :method "post"}
        csrf-token
        [:label "Name:"]
        [:input {:type "text" :name "name"}]
        [:br]
        [:label "Sex:"]
        [:input {:type "text" :name "sex"}]
        [:br]
        [:label "Date of Birth:"]
        [:input {:type "date" :name "date-of-birth"}]
        [:br]
        [:label "Address:"]
        [:input {:type "text" :name "address"}]
        [:br]
        [:label "Social Security Number:"]
        [:input {:type "text" :name "social-security-number"}]
        [:br]
        [:input {:type "submit" :value "Add Patient"}]]

       [:h2 "Delete a patient:"]
       (for [patient patients]
         [:form {:action (str "/patients/" (:id patient)) :method "post"}
          csrf-token
          [:input {:type "hidden" :name "_method" :value "DELETE"}]
          [:input {:type "submit" :value "Delete Patient"}]])])))

(defroutes patient-routes
  (GET "/" [] (patient-form))
  (POST "/patients" [name sex date-of-birth address social-security-number]
    (patients/add-patient name sex date-of-birth address social-security-number)
    {:status 201
     :headers {"Content-Type" "text/html"}
     :body "Patient added successfully!"})
  (GET "/patients" []
    (let [patients (patients/get-all-patients)]
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body (json/generate-string patients)}))
   (DELETE "/patients/:id" [id]
    (patients/delete-patient (Integer/parseInt id))
    {:status 204
     :headers {"Content-Type" "text/html"}
     :body "Patient deleted successfully!"})
  ; Add more routes here
  )
