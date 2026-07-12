(ns budgie.file-test
  (:require [budgie.file :as m]
            [babashka.fs :as fs]
            [clojure.edn :as edn]
            [clojure.test :refer [deftest is testing use-fixtures]]))

(def test-path (atom nil))
(def test-id "some-id")
(def test-entry {:id test-id :some :data})

(defn clean-test-files!
  []
  (when (and @test-path (fs/exists? @test-path)) (fs/delete-tree @test-path)))

(defn with-test-path
  [test-fn]
  (reset! test-path (fs/create-temp-dir {:prefix "budgie-test-"}))
  (try (test-fn) (finally (clean-test-files!) (reset! test-path nil))))

(use-fixtures :each with-test-path)

(deftest write-entry!-test
  (testing "Writing edn-file entry from entry data"
    (with-redefs [m/default-path @test-path]
      (let [expected-path (fs/path @test-path (str test-id ".edn"))
            actual-path (m/write-entry! test-entry)]
        (is (= (str expected-path) (str actual-path)))
        (is (= test-entry (edn/read-string (slurp (str actual-path)))))))))

(deftest read-entry-test
  (testing "Reading edn-file entry data"
    (with-redefs [m/default-path @test-path]
      (m/write-entry! test-entry)
      (is (= test-entry (m/read-entry test-id))))))

(deftest read-entry-missing-file-test
  (testing "Missing entry files return nil"
    (with-redefs [m/default-path @test-path]
      (is (nil? (m/read-entry "missing-id"))))))
