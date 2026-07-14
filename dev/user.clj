(ns user
  (:require [clojure.tools.namespace.repl :as repl]))

(repl/set-refresh-dirs "src")

(defn reload
  "Reloads changed application namespaces under src/."
  []
  (repl/refresh))
