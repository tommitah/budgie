(ns budgie.core
  (:require [budgie.entry :as bentry]
            [budgie.file :as bfile]
            [clojure.pprint :as pp]))

(defn add "Naively adds two numbers together" [x & xs] (apply + x xs))

(defn use-write-entry
  [opts]
  (try (let [{:keys [category amount]} opts
             entry (bentry/create category amount)
             path (bfile/write-entry! entry)
             full-entry (bfile/read-entry :path (str path))]
         (println "Created entry:")
         (pp/pprint full-entry))
       (catch Exception e
         (println "Something went wrong creating entry")
         (pp/pprint e))))

(defn use-list-entry [_opts] (pp/pprint "Mode :list not implemented"))

(defn use-read-entry [opts] (pp/pprint (bfile/read-entry (:id opts))))

(defn -main
  [opts]
  ;; todo: spec out the cli opts in `cli_command`
  (println "Calling with options: " opts)
  (let [mode (:mode opts)]
    (case mode
      :read (use-read-entry opts)
      :write (use-write-entry opts)
      :list (use-list-entry opts)
      (pp/pprint ":mode unimplemented"))))
