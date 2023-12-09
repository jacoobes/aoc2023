(:require '[clojure.string :as str])
(:require '[clojure.java.io :as io])

(defn input [url]  
  (-> (slurp url)
      (str/split-lines)))

(defn period? [c] 
  (= c \.))


(defn digit? [c] 
  (<=  48 (int c) 57))


#_"
  known=
    lines are even length
    . = no symbol
    anything else is symbol
"


(defn get-position [tpk] 
    (->> tpk 
         (filter #(and (number? %) (not= -1 %))) 
         (map int)))

(defn lines->grid [lines] 
  (map #(apply vector %) lines))

(def translations [ #_:t [0 -1] 
                 #_:tr[-1 1]  
                 #_:r [1  0] 
                 #_:br[1  1]
                 #_:b [0 -1]
                 #_:bl[1 -1]
                 #_:l [-1 0] 
                 #_:tl[-1 -1]])

(defn sumnum [nums] 
  (apply + nums))

(defn calc-offset [pos] 
  (map (fn [pr]  
         (map + pos pr)) translations))

(defn get-symbol-pos [grid] 
  (for [[i r] (map-indexed vector grid)
        [j c] (map-indexed vector r) 
        :when (not (or (period? c) (digit? c)))]
        (calc-offset [i j])))

;(map #(get-in grid %))
(defn translate [coords grid] 
  (->> grid 
       (mapv coords  grid)
       ))


(defn solution1 [lines] 
  (let [grid (apply vector (lines->grid lines))
        igrid (map-indexed grid)
        grid-positions (get-symbol-pos grid)]
    (loop [[fst & rest]  grid-positions 
            numbers []]
        (if fst 
          (let [coords (map #(apply vector %) fst)]
            (recur rest 
                   (conj numbers (translate coords igrid)))
            ) 
          igrid))))

(println (solution1 (input (io/resource "./resources/input3.txt"))))
