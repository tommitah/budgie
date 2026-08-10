(ns budgie.http.interceptor)

(def request-handling-duration-interceptor
  {:name :time-measurement-interceptor
   :enter (fn [context]
            (assoc context :start-time (System/nanoTime)))
   :leave (fn [context]
            (let [elapsed (- (System/nanoTime) (:start-time context))
                  request (:request context)
                  {:keys [uri query-string]} request]
              (println "Request to" uri query-string "took" elapsed "ns")
              context))})
