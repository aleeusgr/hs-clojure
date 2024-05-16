(ns hs-clojure.db-test
  (:require [clojure.test :refer :all]
            [hs-clojure.db :refer :all]
            [jdbc.pool.c3p0 :as pool]))

(deftest test-datasource-spec
  (testing "datasource spec is correctly configured"
    (is (map? spec))
    (is (= (:subprotocol spec) "postgresql"))
    (is (= (:subname spec) "//localhost:5432/hs_clojure"))
    (is (= (:user spec) "admin"))
    (is (nil? (:password spec)))))

(deftest test-datasource-connection
  (testing "datasource connection is successful"
    (let [conn (pool/make-datasource spec)]
      (is (instance? java.sql.Connection conn))
      (.close conn))))

(run-tests)
