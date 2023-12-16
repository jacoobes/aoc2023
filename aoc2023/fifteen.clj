(:require '[clojure.java.io :as io])
(:require '[clojure.string :as str])
(require '[flatland.ordered.map :as om])
(require '[utils :as u])

(defn hash [string] 
  (reduce (fn [acc v] 
            (rem (* (+ acc (int v)) 17) 256)) 0 (apply vector string)))

(def input (-> (u/input (io/resource "./resources/input15.txt"))
               (first) ; to ensure we only get the first line!!
               (str/split #",")))

(defn instruction [string]
  (if (str/includes? string "=")
    (let [[k v] (str/split string #"=")] 
      { :key (keyword k)
        :value (read-string v) 
        :box-num (hash k)
        :func assoc })
    (let [k (subs string 0 (str/index-of string "-"))] 
      { :key (keyword k) 
        :box-num (hash k)
        :func dissoc })))

(defn boxes []
  (vec (repeat 256 (om/ordered-map))))

(defn hashmap [arr] 
  (let [instructions (map instruction arr)] 
    (reduce 
      (fn [acc {:keys [box-num key func value] }] 
        (update acc box-num func key value)) 
      (boxes) instructions)))

(defn focus-power [boxes] 
  (reduce + (for [[i box] (map-indexed vector boxes)
                  [j [_ focal]] (map-indexed vector box)]
              (* (inc i) (inc j) focal))))


(let [s1 (reduce + (map hash input))
      s2 (hashmap input)]
  (println s1)
  (println (focus-power s2))
  
  ) 
