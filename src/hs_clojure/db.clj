(ns hs-clojure.db
  (:require [clojure.java.jdbc :as jdbc]
            [jdbc.pool.c3p0 :as pool]))

(def spec
  (pool/make-datasource-spec
   {:subprotocol "postgresql"
    :subname "//localhost:5432/hs_clojure"
    :user "admin"
    :password ""}))

(defn create-patients-table []
  (jdbc/with-db-transaction [tx spec] 
    (jdbc/db-do-commands tx 
                         "CREATE TABLE IF NOT EXISTS patients (
                          id bigserial PRIMARY KEY,
                          name varchar NOT NULL,
                          sex varchar NOT NULL,
                          date_of_birth date NOT NULL,
                          address text NOT NULL,
                          social_security_number varchar(11) NOT NULL,
                          created_at timestamp NOT NULL default current_timestamp)")))

(create-patients-table) 
