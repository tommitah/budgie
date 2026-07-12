(ns budgie.core)

(defn add "Naively adds two numbers together" [x & xs] (apply + x xs))

(defn main [opts] (println "Calling with options") (println opts))
