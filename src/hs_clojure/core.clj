(ns hs-clojure.core
  (:gen-class)
  (:require [hs-clojure.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn -main
  "Start the Ring server"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (run-jetty app {:port port :join? false})))
