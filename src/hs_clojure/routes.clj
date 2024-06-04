(ns hs-clojure.routes
  (:require [compojure.core :refer :all]
            [cheshire.core :as json]
            [hs-clojure.patients :as patients]
            [hs-clojure.db :as db]
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
          [:input {:type "submit" :value "Delete Patient"}]])
       [:h2 "Edit a patient:"]
       [:ul
        (for [patient patients]
          [:li
           [:a {:href (str "/patients/" (:id patient) "/edit")} "Edit " (:name patient)]])]])))
(defn patient-edit-form [patient]
  (html5
    [:head
     [:title "Edit Patient"]]
    [:body
     [:h2 "Edit Patient"]
     [:form {:action (str "/patients/" (:id patient)) :method "post"}
      (anti-forgery-field)
      [:label "Name:"]
      [:input {:type "text" :name "name" :value (:name patient)}]
      [:br]
      [:label "Sex:"]
      [:input {:type "text" :name "sex" :value (:sex patient)}]
      [:br]
      [:label "Date of Birth:"]
      [:input {:type "date" :name "date-of-birth" :value (:date_of_birth patient)}]
      [:br]
      [:label "Address:"]
      [:input {:type "text" :name "address" :value (:address patient)}]
      [:br]
      [:label "Social Security Number:"]
      [:input {:type "text" :name "social-security-number" :value (:social_security_number patient)}]
      [:br]
      [:input {:type "submit" :value "Update Patient"}]]]))

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
   (GET "/patients/:id/edit" [id]
    (let [patient (patients/get-patient-by-id db/spec (Integer/parseInt id))]
      {:status 200
       :headers {"Content-Type" "text/html"}
       :body (patient-edit-form patient)}))
  (POST "/patients/:id" [id name sex date-of-birth address social-security-number]
    (patients/update-patient id name sex date-of-birth address social-security-number)
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body "Patient updated successfully!"})
  ; Add more routes here
  )
