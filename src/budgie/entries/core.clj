(ns budgie.entries.core
  (:require [budgie.entries.model :as m]
            [budgie.entries.file-storage :as fs]
            [clojure.pprint :as pp]))

(defn query [opts] (pp/pprint (s/read-entry (:id opts))))

(defn list-all [_opts] (pp/pprint (s/read-entries)))

(defn create
  [opts]
  (try (let [{:keys [category amount]} opts
             entry (m/make-entry category amount)
             path (fs/write-entry! entry)
             full-entry (fs/read-entry :path (str path))]
         (println "Created entry:")
         (pp/pprint full-entry))
       (catch Exception e
         (println "Something went wrong creating entry")
         (pp/pprint e))))
