(ns hs-clojure.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [hs-clojure.handler :refer [app]]
            [hs-clojure.test-helpers :refer [clean-database]]
            [hs-clojure.patients :as patients]))

(use-fixtures :each clean-database)

(deftest test-root
  (testing "root"
    (let [response (app (mock/request :get "/"))]
      (is (= 200 (:status response)))
      (is (= "Hello World" (:body response))))))

(deftest test-create-patient
  (testing "create patient"
    (let [response (app (-> (mock/request :post "/patients")
                         (assoc-in [:headers "Content-Type"] "application/json")
                         (assoc-in [:headers "Accept"] "application/json")
                         (assoc-in [:body] "{\"name\":\"John\",\"sex\":\"Male\",\"date-of-birth\":\"2020-01-01\",\"address\":\"123 Main St\",\"social-security-number\":\"123-45-6789\"}")))])))

(deftest test-update-patient
  (testing "update patient"
    (let [response (app (-> (mock/request :get "/patients")
                         (assoc-in [:headers "Content-Type"] "application/json")
                         (assoc-in [:query-params "name"] "John")
                         (assoc-in [:query-params "sex"] "Male")
                         (assoc-in [:query-params "date-of-birth"] "2020-01-01")
                         (assoc-in [:query-params "address"] "123 Main St")
                         (assoc-in [:query-params "social-security-number"] "123-45-6789")))])))

(deftest test-get-patient
  (testing "get patient"
    (let [response (app (mock/request :get "/patients/1"))
          body (:body response)]
      (is (= 200 (:status response)))
      (is (not-empty body)))))

(deftest test-patients
  (testing "patients"
    (let [response (app (mock/request :get "/patients"))
          body (:body response)]
      (is (= 200 (:status response)))
      (is (not-empty body)))))

(deftest test-delete-patient
  (testing "delete patient"
    (let [response (app (mock/request :delete "/patients/1"))
          body (:body response)]
      (is (= 200 (:status response)))
      (is (not-empty body)))))
