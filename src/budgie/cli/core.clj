(ns budgie.cli.core
  (:require [budgie.entries.core :as e]
            [clojure.pprint :as pp]))

(defn -main
  "Main entry point for the cli program that runs budgie"
  [opts]
  ;; todo: spec out the cli opts in `command`
  (println "Calling with options: " opts)
  (let [mode (:mode opts)]
    (case mode
      :read (e/query opts)
      :write (e/create opts)
      :list (e/list-all opts)
      (pp/pprint ":mode unimplemented"))))

