(ns budgie.http.routes
  (:require [budgie.http.util :as util]))

(defn greet-handler [_request] (util/ok "Hello, world!\n"))

(def routes
  #{["/greet" :get greet-handler :route-name :greet]
    ["/entry" :post util/echo :route-name :entry-create]
    ["/entry" :get util/echo :route-name :entry-list-query-form]
    ["/entry/:item-id" :get util/echo :route-name :entry-get]
    ["/entry/:item-id" :put util/echo :route-name :entry-update]})
