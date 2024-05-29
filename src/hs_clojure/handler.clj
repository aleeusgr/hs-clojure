(ns hs-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.resource :refer [wrap-resource]]
            [hs-clojure.routes :refer [patient-routes]]))

(defroutes app-routes
  (route/resources "/")
  (GET "/" [] (hs-clojure.routes/patient-form))
  patient-routes
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      (wrap-resource "public")))
