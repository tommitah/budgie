(ns budgie.entries.list
  (:require [budgie.entries.storage :as storage]
            [clojure.pprint :as pp]))

(defn run [_opts] (pp/pprint (storage/read-entries)))
