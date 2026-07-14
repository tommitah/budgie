(ns budgie.entries.list
  (:require [budgie.entries.storage :as storage]
            [clojure.pprint :as pp]))

(defn list-entries [_opts] (pp/pprint (storage/read-entries)))
