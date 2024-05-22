(ns hs-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.resource :refer [wrap-resource]]))

(defroutes app-routes
  (route/resources "/") ; Serve static files from resources/public
  (GET "/" [] (ring.util.response/redirect "/index.html")) ; Redirect to index.html
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      (wrap-resource "public")))
