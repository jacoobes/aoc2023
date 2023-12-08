(ns seventh
  (:require [utils :as u]
            [clojure.java.io :as io]
            [clojure.core.match :refer [match]]))

(def test "32T3K 765")

(defn into-pair [s] 
  (let [[hand wager] (str/split s #" ")]  
    [(frequencies hand) (Integer/parseInt wager)]))


(defn four-kind? [map]
  (some #{ 4 1 } (vals map)))

(defn determine-kind [[hand _]] 
  (let [handcount (count hand)]
    (cond 
      (= handcount 5) (assoc hand :type :high-card)
      (= handcount 4) (assoc hand :type :one-pair)
      (= handcount 3) (if (some #{2} hand)
                        (assoc hand :type :two-pair)
                        (assoc hand :type :three-kind)) 
      (= handcount 4) (if (some #{3} hand)
                        (assoc hand :type :full-house)
                        (assoc hand :type :four-kind))
      (= handcount 1) (assoc hand :type :five-kind))))


(defn -main [ ]
  (with-open [rdr (io/reader "./aoc2023/input7.txt")]
      (->> (line-seq rdr)
           (map into-pair)))


