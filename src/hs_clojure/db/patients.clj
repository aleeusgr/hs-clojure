(ns hs-clojure.db.patients 
  (:require [hugsql.core :as hugsql])) 
 
(hugsql/def-db-fns "hs_clojure/db/sql/patients.sql")
