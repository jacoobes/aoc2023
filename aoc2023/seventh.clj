(ns seventh
  (:require [utils :as u]))

(def test "32T3K 765")



(defn into-pair [s] 
  (let [[hand wager] (str/split s #" ")]  
    [(frequencies hand) (Integer/parseInt wager)]))

(defn solution [lines]
  (for [line lines] 
   ( ->> line 
         (map into-pair))))

(defn -main [ ]

  )


