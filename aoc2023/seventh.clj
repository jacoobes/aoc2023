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

(defn sort-hands [[hand-a _] [hand-b _]] 
  (let [[counta countb] [(count hand-a) (count hand-b)]
        type (abs (- counta countb))]
        (cond 
          (or (= counta 2) (= countb 2)) (if (four-kind? hand-a)
                                             1
                                             -1)
          (zero? type) (compare (-> hand-a first first) 
                                ( -> hand-b first first))

          :else (compare countb counta)
          )
    ))


(defn -main [ ]
  (with-open [rdr (io/reader "./aoc2023/input7.txt")]
      (->> (line-seq rdr)
           (map into-pair)
          ))


