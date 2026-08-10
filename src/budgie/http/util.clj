(ns budgie.http.util)

(defn response
  [status body & {:as headers}]
  {:status status :body body :headers headers})

(def ok (partial response 200))
(def created (partial response 201))
(def accepted (partial response 202))

;; This is an interceptor
(def echo
  {:name :echo
   :enter (fn [context]
            (let [request (:request context)
                  ;; OK using `ok` here as if it is a function is a bit
                  ;; wacky and hard to parse
                  response (ok request)]
              (assoc context :response response)))})
