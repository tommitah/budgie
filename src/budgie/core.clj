(ns budgie.core
  (:require [budgie.entries.list :as list-entry]
            [budgie.entries.read :as read-entry]
            [budgie.entries.write :as write-entry]
            [clojure.pprint :as pp]))

(defn add "Naively adds two numbers together" [x & xs] (apply + x xs))

(defn -main
  [opts]
  ;; todo: spec out the cli opts in `cli_command`
  (println "Calling with options: " opts)
  (let [mode (:mode opts)]
    (case mode
      :read (read-entry/run opts)
      :write (write-entry/run opts)
      :list (list-entry/run opts)
      (pp/pprint ":mode unimplemented"))))

(comment
  (-main {:mode :write :category :leisure :amount -100})
  (-main {:mode :list})
  (-main {:mode :read :id "some-id"}))
