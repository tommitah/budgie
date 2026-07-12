(ns budgie.core
  (:require [budgie.entry :as budget-entry]))

(defn add "Naively adds two numbers together" [x & xs] (apply + x xs))

(defn main
  [opts]
  ;; todo: spec out the cli opts in `cli_command`
  (println "Calling with options: " opts)
  (let [{:keys [category amount]} opts
        entry (budget-entry/create category amount)]
    (println "Created entry:")
    (println entry)
    ;; todo: save the entry in a file (new file per entry, named via uuid)
  ))

(comment
  (budget-entry/create :leisure -100)
  (budget-entry/create :hobby 201.45))
