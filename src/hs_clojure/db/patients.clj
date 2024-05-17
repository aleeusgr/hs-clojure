; namespace declaration
(ns hs-clojure.db.patients
  (:require [hugsql.core :as hugsql])) ;This statement imports the hugsql.core namespace and assigns it the alias hugsql. This allows the code to use the functions and macros from the hugsql.core namespace with the hugsql prefix.

; HugSQL is a Clojure library that provides a way to manage SQL queries as Clojure functions. It allows you to define SQL queries in a separate file and then generate Clojure functions to execute those queries.

(hugsql/def-db-fns "hs_clojure/db/sql/patients.sql")
; This line uses the def-db-fns macro from HugSQL to define a set of database functions based on the SQL queries defined in the file
