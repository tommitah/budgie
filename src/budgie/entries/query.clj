(ns budgie.entries.query
  (:require [budgie.entries.storage :as storage]
            [clojure.pprint :as pp]))

(defn query-entry [opts] (pp/pprint (storage/read-entry (:id opts))))
