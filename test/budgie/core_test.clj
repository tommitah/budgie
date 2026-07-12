(ns budgie.core-test
  (:require [budgie.core :as m]
            [clojure.test :refer [deftest testing is]]))

(deftest add-test (testing "1 + 2 = 3" (is (= 3 (m/add 1 2)))))

(deftest multiple-param-add-test
  (testing "Testing n-amount of params"
    (and (is (= 3 (m/add 1 1 1)))
         (is (= 4 (m/add 2 2 0)))
         (is (= 9 (m/add 2 1 1 1 2 2))))))
