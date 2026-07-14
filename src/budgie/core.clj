(ns budgie.core
  (:require [clojure.pprint :as pp]
            [budgie.http.web-server :as ws]))

(defn -main [& args] (pp/pprint "Starting Web Server...") (ws/start args))
