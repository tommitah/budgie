(ns budgie.entry
  (:require [clojure.spec.alpha :as s]))

;; --------------- DOMAIN MODEL --------------- ;;
;; This is the data a budget entry line consists of:
(s/def :entry/id uuid?)
(s/def :entry/category #{:leisure :housing :travel :grocery :experience :hobby})
(s/def :entry/created-at inst?)
(s/def :entry/updated-at (s/nilable inst?))
(s/def :entry/type #{:expense :income})
(s/def :entry/amount number?)
(s/def :entry/transaction (s/keys :req-un [:entry/type :entry/amount]))

(s/def :entry/item
  (s/keys :req-un [:entry/id :entry/category :entry/transaction
                   :entry/created-at :entry/updated-at]))

(defn create
  [category transaction-type transaction-amount]
  {:id (random-uuid),
   :category category,
   :transaction {:type transaction-type, :amount transaction-amount},
   :created-at (java.util.Date.),
   :updated-at nil})

(s/fdef create
  :args (s/cat :category :entry/category
               :transaction-type :entry/type
               :transaction-amount :entry/amount)
  :ret :entry/item)
