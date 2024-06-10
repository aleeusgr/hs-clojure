(ns hs-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.session.cookie :refer [cookie-store]]
            [hs-clojure.routes :refer [patient-routes]]))

(def secret-key (byte-array 16 (map byte (range 16))))
(def session-store (cookie-store {:key secret-key}))

(defroutes app-routes
  (route/resources "/")
  (GET "/" [] (hs-clojure.routes/patient-form))
  patient-routes
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-session {:store session-store})
      (wrap-defaults site-defaults)
      (wrap-resource "public")))
