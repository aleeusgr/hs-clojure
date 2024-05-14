(ns hs-clojure.app
  (:require [hs-clojure.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
