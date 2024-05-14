(ns hs-clojure.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [hs-clojure.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[hs-clojure started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[hs-clojure has shut down successfully]=-"))
   :middleware wrap-dev})
