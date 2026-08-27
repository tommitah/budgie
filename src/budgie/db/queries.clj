(ns budgie.db.queries
  (:require [honey.sql :as sql]
            [honey.sql.helpers :as h]))

(defn test-query
  []
  (-> (h/select :*)
      (h/from :test_table)
      sql/format))
