(ns hs-clojure.db
 (:require [jdbc.pool.c3p0 :as pool])) 
 
(def spec 
  (pool/make-datasource-spec 
    {:subprotocol "postgresql" 
     :subname "//localhost:5432/hs_clojure" 
     :user "admin" 
     :password ""}))
