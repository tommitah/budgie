(ns budgie.entries.model-test
  (:require [budgie.entries.model :as entry]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.test.alpha :as stest]
            [clojure.test :refer [deftest is testing]]))

(deftest entry-spec-generates-valid-values
  (testing "Budget entry spec"
    (let [entries (gen/sample (s/gen :entry/item) 10)]
      (is (= 10 (count entries)))
      (is (every? #(s/valid? :entry/item %) entries)))))

(deftest create-check
  (testing "entry/create satisfies its function spec"
    (let [results (stest/check `entry/make-entry)]
      (is (= 1 (count results)))
      (is (every? nil? (map :failure results))))))
