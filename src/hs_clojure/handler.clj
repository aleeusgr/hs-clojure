; src/hs_clojure/handler.clj
(ns hs-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.resource :refer [wrap-resource]]
            [hs-clojure.routes :refer [patient-routes]]))

(defroutes app-routes
  (route/resources "/") ; Serve static files from resources/public
  (GET "/" [] (ring.util.response/redirect "/index.html")) ; Redirect to index.html
  patient-routes ; Include the patient routes
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      (wrap-resource "public")))
