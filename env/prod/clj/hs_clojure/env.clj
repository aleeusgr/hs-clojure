(ns hs-clojure.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[hs-clojure started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[hs-clojure has shut down successfully]=-"))
   :middleware identity})
