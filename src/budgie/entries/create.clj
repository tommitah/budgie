(ns budgie.entries.create
  (:require [budgie.entries.model :as entry]
            [budgie.entries.storage :as storage]
            [clojure.pprint :as pp]))

(defn create-entry
  [opts]
  (try (let [{:keys [category amount]} opts
             entry (entry/create category amount)
             path (storage/write-entry! entry)
             full-entry (storage/read-entry :path (str path))]
         (println "Created entry:")
         (pp/pprint full-entry))
       (catch Exception e
         (println "Something went wrong creating entry")
         (pp/pprint e))))
