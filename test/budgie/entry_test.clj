(ns budgie.entry-test
  (:require [budgie.entry :as entry]
            [clojure.test :refer [deftest testing is]]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.test.alpha :as stest]))

(deftest entry-spec-generates-valid-values
  (testing "Budget entry spec"
    (let [entries (gen/sample (s/gen :entry/item) 10)]
      (is (= 10 (count entries)))
      (is (every? #(s/valid? :entry/item %) entries)))))

(deftest create-check
  (testing "entry/create satisfies its function spec"
    (let [results (stest/check `entry/create)]
      (is (= 1 (count results)))
      (is (every? nil? (map :failure results))))))
