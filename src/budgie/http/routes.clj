(ns budgie.http.routes
  (:require [budgie.http.util :as util]))

(defn greet-handler [_request] (util/ok "Hello, world!\n"))

(def routes
  #{["/greet" :get greet-handler :route-name :greet]
    ["/entry" :post util/echo :route-name :list-create]
    ["/entry" :get util/echo :route-name :list-query-form]
    ["/entry/:list-id" :get util/echo :route-name :list-view]
    ["/entry/:list-id" :post util/echo :route-name :list-item-create]
    ["/entry/:list-id/:item-id" :get util/echo :route-name :list-item-view]
    ["/entry/:list-id/:item-id" :put util/echo :route-name :list-item-update]})
