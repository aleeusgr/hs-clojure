DROP DATABASE IF EXISTS hs_clojure;

CREATE DATABASE hs_clojure;

GRANT ALL PRIVILEGES ON DATABASE hs_clojure TO admin;

CREATE TABLE patients (
  id bigserial PRIMARY KEY,
  name varchar NOT NULL,
  sex varchar NOT NULL,
  date_of_birth date NOT NULL,
  address text NOT NULL,
  social_security_number varchar(11) NOT NULL,
  created_at timestamp NOT NULL default current_timestamp
)
