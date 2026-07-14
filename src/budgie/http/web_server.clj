(ns budgie.http.web-server
  (:require [io.pedestal.connector :as conn]
            [io.pedestal.http.http-kit :as hk]
            [budgie.http.routes :as r]
            [io.pedestal.connector.test :as test]))

(defonce ^:private *connector (atom nil))

(def ^:private default-port 8890)
(defn create-connector
  [{:keys [port]}]
  (println "Connecting to port" (or port default-port))
  (-> (conn/default-connector-map (or port default-port))
      (conn/with-default-interceptors)
      (conn/with-routes r/routes)
      (hk/create-connector nil)))

(defn start
  [opts]
  (->> (create-connector opts)
       conn/start!
       (reset! *connector)))

(defn stop [] (conn/stop! @*connector) (reset! *connector nil))

(defn restart [] (when @*connector (stop)) (start {}))

(defn- test-request
  "Run simple test request to any endpoint running on pedestal via `(test-request :get \"/entry\")`"
  [verb url]
  (test/response-for @*connector verb url))

(comment
  (test-request :get "/entry")
  (dissoc *1 :body)
  (start {})
  (stop)
  (restart))
