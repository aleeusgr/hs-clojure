(ns hs-clojure.test-helpers
  (:require [hs-clojure.db :as db]
            [hs-clojure.db.patients :as db-patients]))

(defn clean-database [test-fn]
  (db-patients/drop-patients-table db/spec)
  (db-patients/create-patients-table db/spec)
  (test-fn))
