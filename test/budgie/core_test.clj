(ns budgie.core-test
  (:require
    [budgie.core :refer [add]]
    [clojure.test :refer [deftest testing is]]))


(deftest test-add
  (testing "1 + 2 = 3"
    (is (= 3 (add 1 2)))))
