(ns budgie.file
  (:require [babashka.fs :as fs]
            [clojure.edn :as edn]))

(def ^:private default-path
  (fs/path (System/getProperty "user.home") ".budgie" "storage" "file"))

(defn- ensure-dir! [path] (fs/create-dirs path))

(defn- entry-file-path [id] (fs/path default-path (str id ".edn")))

(defn write-entry!
  "Writes budget entry contents into a file named via the uuid of the entry data.\n
  Ensures the file directory exists on the local machine.\n
  Returns file path."
  [entry]
  (ensure-dir! default-path)
  (let [path (entry-file-path (:id entry))]
    (spit (str path) (pr-str entry))
    path))

(defn read-entry
  "Reads budget entry from a file via id, or from a raw file path with `:path`."
  ([id] (read-entry :id id))
  ([source value]
   (let [path (case source
                :id (entry-file-path value)
                :path value)]
     (try (-> path
              (str)
              (slurp)
              (edn/read-string))
          (catch java.io.FileNotFoundException _e nil)))))

(comment
  (write-entry! {:id "some_id" :foo "bar"})
  (str (entry-file-path "some_id"))
  (read-entry "some_id")
  (read-entry :path (entry-file-path "some_id"))
  (pr-str {:id "some-id" :foo :bar})
  (str (fs/path default-path (str (:id {:id "some-id" :foo :bar}) ".edn"))))
