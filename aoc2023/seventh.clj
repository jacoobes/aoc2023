(ns seventh
  (:require [utils :as u]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def test 
"32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483")

(def translate-chars { \A \Z \K \Y \Q \X \J \W \T \V })

(defn translate-ordering [s]
  (reduce #(str % (translate-chars %2 %2)) "" s))

(defn into-pair [s] 
  (let [[hand wager] (str/split s #" ")]  
    (assoc { :hand hand 
             :wager (Integer/parseInt wager) 
             :hand-ordered (translate-ordering hand) } 
           :frequencies (frequencies hand) )))

(defn determine-kind [hand] 
  (let [handcount (count (:frequencies hand))]
    (cond 
      (= handcount 5) (assoc hand :value 1)
      (= handcount 4) (assoc hand :value 2)
      (= handcount 3) (if (some #{2} hand)
                        (assoc hand :value 3)
                        (assoc hand :value 4))
      (= handcount 2) (if (some #{3} hand)
                        (assoc hand :value 5)
                        (assoc hand :value 6))
      (= handcount 1) (assoc hand :value 7))))

(defn price [rank wager]
  (* rank wager))

(def juxtfn (juxt :value  
                  :hand-ordered ))
(defn det-ranks [] 
  
  )

(defn -main [ ]
    (println (->> (u/input "./aoc2023/input7.txt")
                  (map into-pair)
                  (map determine-kind)
                  (sort-by juxtfn))))


