-- :name create-patients-table :! 
CREATE TABLE IF NOT EXISTS patients (
  id bigserial PRIMARY KEY,
  name varchar NOT NULL,
  sex varchar NOT NULL,
  date_of_birth date NOT NULL,
  address text NOT NULL,
  social_security_number varchar(11) NOT NULL,
  created_at timestamp NOT NULL default current_timestamp
)

-- :name drop-patients-table :! 
DROP TABLE IF EXISTS patients

-- :name insert-patient :! :n 
INSERT INTO patients (name, sex, date_of_birth, address, social_security_number)
VALUES (:name, :sex, :date_of_birth, :address, :social_security_number)

-- :name get-all-patients :? :* 
SELECT * FROM patients

-- :name get-patient-by-name-like :? :1 
SELECT * FROM patients WHERE name LIKE :name-like

-- :name get-patient-by-id :? :1 
SELECT * FROM patients WHERE id = :id

-- :name update-patient :! :n 
UPDATE patients 
SET name = :name, sex = :sex, date_of_birth = :date_of_birth, address = :address, social_security_number = :social_security_number
WHERE id = :id

-- :name delete-patient :! :n 
DELETE FROM patients WHERE id = :id
