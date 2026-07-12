(ns budgie.file
  (:require [babashka.fs :as fs]))

(def default-path
  (fs/path (System/getProperty "user.home") ".budgie" "storage" "file"))

(defn -ensure-dir! [path] (fs/create-dirs path))

(defn -entry-file-path [entry] (fs/path default-path (str (:id entry) ".edn")))

(defn write-entry!
  "Writes budget entry contents into a file named via the uuid of the entry data.\n
  Ensures the file directory exists on the local machine.\n
  Returns file path."
  [entry]
  (-ensure-dir! default-path)
  (let [path (-entry-file-path entry)]
    ;; write the entry edn contents ar a str in path
    (spit (str path) (pr-str entry))
    path))

(comment
  (write-entry! {:id "some_id" :foo "bar"})
  (pr-str {:id "some-id" :foo :bar})
  (str (fs/path default-path (str (:id {:id "some-id" :foo :bar}) ".edn"))))
