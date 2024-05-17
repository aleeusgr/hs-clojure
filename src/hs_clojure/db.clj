(ns hs-clojure.db
  (:require [clojure.java.jdbc :as jdbc]
            [jdbc.pool.c3p0 :as pool]))

(def spec
  (pool/make-datasource-spec
   {:subprotocol "postgresql"
    :subname "//localhost:5432/hs_clojure"
    :user "admin"
    :password ""}))

; create a database table named patients if it doesn't already exist. Function takes no arguments.
(defn create-patients-table []
  (jdbc/with-db-transaction [tx spec] ; macro is used to execute a database transaction. It takes two arguments:
    ; tx: a symbol that will be bound to the transaction object
    ; spec: a database specification (e.g., a connection string or a database object)
    (jdbc/db-do-commands tx ;executes a sequence of SQL commands on the database.
                         "CREATE TABLE IF NOT EXISTS patients (
                          id bigserial PRIMARY KEY,
                          name varchar NOT NULL,
                          sex varchar NOT NULL,
                          date_of_birth date NOT NULL,
                          address text NOT NULL,
                          social_security_number varchar(11) NOT NULL,
                          created_at timestamp NOT NULL default current_timestamp)")))

(create-patients-table) ; function is called
