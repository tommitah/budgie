(ns user)

(defn reload
  "This evaluates the entry point for the program and makes it possible to start
  evaluating single forms instantly when the dev repl is started.\n
  Without it, all namespaces need to be separately evaluated."
  []
  (require '[budgie.core :as core] :reload-all)
  :reloaded)

;; Instantly evaluate the namespaces in the project when starting the dev repl
;; with
;; `Clj -M:dev` (see `deps.edn` aliases)
(reload)
