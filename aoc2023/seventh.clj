(require '[utils :as u])
(:require '[clojure.java.io :as io])
(:require '[clojure.string :as str])

(def test 
"32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483")

(def translate-chars { \A \Z \K \Y \Q \X \J \W \T \V })

(defn translate-ordering [s]
  (reduce #(str % (translate-chars %2 %2)) "" s))

(defn into-pair [i s] 
  (let [[hand wager] (str/split s #" ")]  
    (assoc {:hand hand 
            :wager (Integer/parseInt wager) 
            :hand-ordered (translate-ordering hand)} 
           :frequencies (frequencies hand))))

(defn determine-kind [hand] 
  (let [handcount (count (:frequencies hand))
        #_ (println (some #{2} ))
        ]
    (cond 
      (= handcount 5) (assoc hand :value 1) ;high card
      (= handcount 4) (assoc hand :value 2) ;one pair
      (= handcount 3) (if (some #{2} (vals (:frequencies hand)))  ;disambiguate 2 pair and 3 kind 
                        (assoc hand :value 3) ; 2 pair
                        (assoc hand :value 4)) ;3 kind
      (= handcount 2) (if (some #{3} (vals (:frequencies hand))) ;disambiguate 4 kind and fullhouse
                        (assoc hand :value 5) ;full house
                        (assoc hand :value 6)) ;4 kind
      (= handcount 1) (assoc hand :value 7)))) ;5 kind



(def juxtfn (juxt :value  
                  :hand-ordered))

(println (->> (u/input (io/resource "./resources/input7.txt") #_test)
              (map-indexed into-pair)
              (map determine-kind)
              (group-by :value)
              (into (sorted-map))
              (map #(sort-by juxtfn (second %)))
              (apply concat)
              (map-indexed #(* (inc %) (:wager %2)))
              (apply +)))


