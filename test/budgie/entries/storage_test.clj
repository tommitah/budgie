(ns budgie.entries.storage-test
  (:require [babashka.fs :as fs]
            [budgie.entries.storage :as storage]
            [clojure.edn :as edn]
            [clojure.test :refer [deftest is testing use-fixtures]]))

(def test-path (atom nil))
(def test-id "some-id")
(def test-entry {:id test-id :some :data})

(defn clean-test-files!
  []
  (when (and @test-path (fs/exists? @test-path))
    (fs/delete-tree @test-path)))

(defn with-test-path
  [test-fn]
  (reset! test-path (fs/create-temp-dir {:prefix "budgie-test-"}))
  (try
    (test-fn)
    (finally
      (clean-test-files!)
      (reset! test-path nil))))

(use-fixtures :each with-test-path)

(deftest write-entry!-test
  (testing "Writing edn-file entry from entry data"
    (with-redefs [storage/default-path @test-path]
      (let [expected-path (fs/path @test-path (str test-id ".edn"))
            actual-path (storage/write-entry! test-entry)]
        (is (= (str expected-path) (str actual-path)))
        (is (= test-entry (edn/read-string (slurp (str actual-path)))))))))

(deftest read-entry-test
  (testing "Reading edn-file entry data"
    (with-redefs [storage/default-path @test-path]
      (storage/write-entry! test-entry)
      (is (= test-entry (storage/read-entry test-id))))))

(deftest read-entry-from-path-test
  (testing "Reading edn-file entry data from a raw file path"
    (with-redefs [storage/default-path @test-path]
      (let [path (storage/write-entry! test-entry)]
        (is (= test-entry (storage/read-entry :path path)))))))

(deftest read-entries-test
  (testing "Reading all edn-file entries"
    (with-redefs [storage/default-path @test-path]
      (let [other-entry {:id "other-id" :some :other-data}]
        (storage/write-entry! test-entry)
        (storage/write-entry! other-entry)
        (is (= #{test-entry other-entry} (set (storage/read-entries))))))))

(deftest read-entry-missing-file-test
  (testing "Missing entry files return nil"
    (with-redefs [storage/default-path @test-path]
      (is (nil? (storage/read-entry "missing-id"))))))
