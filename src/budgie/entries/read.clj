(ns budgie.entries.read
  (:require [budgie.entries.storage :as storage]
            [clojure.pprint :as pp]))

(defn run [opts] (pp/pprint (storage/read-entry (:id opts))))
