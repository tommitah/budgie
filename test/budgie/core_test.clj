(ns budgie.core-test
  (:require
    [budgie.core :refer [add]]
    [clojure.test :refer [deftest testing is]]))


(deftest add-test
  (testing "1 + 2 = 3"
    (is (= 3 (add 1 2)))))


(deftest multiple-param-test
  (testing "Testing n-amount of params"
    (and (is (= 3 (add 1 1 1)))
         (is (= 4 (add 2 2 0)))
         (is (= 9 (add 2 1 1 1 2 2))))))
