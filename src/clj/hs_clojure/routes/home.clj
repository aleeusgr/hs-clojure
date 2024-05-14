(ns hs-clojure.routes.home
  (:require
   [hs-clojure.layout :as layout]
   [hs-clojure.db.core :as db]
   [clojure.java.io :as io]
   [hs-clojure.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))



(defn home-page [request]
  (layout/render request "home.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "docs/docs.md" io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]])

