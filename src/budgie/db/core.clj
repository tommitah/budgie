(ns budgie.db.core
  (:require [next.jdbc.connection :as connection]
            [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs])
  ;; Java module
  (:import (com.zaxxer.hikari HikariDataSource)))

(def ^:private opts
  {:jdbcUrl "jdbc:postgresql://localhost:5432/local-budgie-db"
   :username "foo"
   :password "bar"
   :maximum-pool-size 10})

(def datasource (connection/->pool HikariDataSource opts))

(def default-jdbc-opts
  {:builder-fn rs/as-unqualified-lower-maps
   ;; query timeout is in seconds
   :timeout 5})

(defn alive?
  "Checks whether database can be connected to."
  []
  (try (jdbc/execute-one! datasource ["select 1"] default-jdbc-opts)
       true
       (catch Exception _ false)))

(defn execute!
  ([sql] (jdbc/execute! datasource sql default-jdbc-opts))
  ([sql opts] (jdbc/execute! datasource sql (merge default-jdbc-opts opts))))

;; essentially take-first
(defn execute-one!
  ([sql] (jdbc/execute-one! datasource sql default-jdbc-opts))
  ([sql opts]
   (jdbc/execute-one! datasource sql (merge default-jdbc-opts opts))))

;; TODO: write a migration to create table(s)
(comment
  (execute! #p ["select * from test_table"])
  (execute! ["insert into test_table (name) values (?)" "foobar"])
  (execute!
    ["create table if not exists test_table (
                                    id bigserial primary key,
                                    name text not null,
                                    created_at timestamptz not null default now())"]))
