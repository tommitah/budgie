(ns budgie.cli.core
  (:require [budgie.entries.create :as ec]
            [budgie.entries.list :as el]
            [budgie.entries.query :as eq]
            [clojure.pprint :as pp]))

(defn -main
  "Main entry point for the cli program that runs budgie"
  [opts]
  ;; todo: spec out the cli opts in `command`
  (println "Calling with options: " opts)
  (let [mode (:mode opts)]
    (case mode
      :read (eq/query-entry opts)
      :write (ec/create-entry opts)
      :list (el/list-entries opts)
      (pp/pprint ":mode unimplemented"))))

