; src/hs_clojure/handler_test.clj
(ns hs-clojure.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [hs-clojure.handler :refer [app]]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= 302 (:status response)))
      (is (= "http://localhost/index.html" (get-in response [:headers "Location"]))))) 

  (testing "not found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= 404 (:status response)))
      (is (= "Not Found" (:body response)))))

  (testing "static resources"
    (let [response (app (mock/request :get "/public/test.txt"))]
      (is (= 404 (:status response))))))
