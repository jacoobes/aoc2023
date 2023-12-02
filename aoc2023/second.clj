(require '[clojure.string :as str])

(defn input [path]  
  (-> (slurp path)
      (str/split-lines)))

(defn idexof-semi[s] 
    (str/index-of s ";"))

(defn parse-pairs [sq] 
    (->> sq (map #(str/split % #" "))
            (map #(vec [(second %) (Integer/parseInt (first %))]))))

(defn str->map [v]
     (->> v (map #(re-seq #"\d+ \w+" %))
            (map parse-pairs)
            (map #(into {} %))))

(def threshold { "red" 12 "green" 13 "blue" 14 })

(defn parse [line] 
  (let [split-index (str/index-of line ":" 5)] 
    (->> line (map #(subs % split-index))
              (map #(str/split % #"; "))
              (map str->map))))

(defn cmp-map [m] 
  (and (<= (m "red" 0) (threshold "red"))
       (<= (m "blue" 0) (threshold "blue"))
       (<= (m "green" 0) (threshold "green"))))


(defn determine-possible [idx v]
  (let [below-threshold? #(cmp-map %) ] 
    (if (every? below-threshold? v) 
      (inc idx)
      0)))

(defn solution-1 [arr] 
    (->> arr 
         (map-indexed determine-possible)
         (apply +)))


(defn solution-2 [arr]
    (->> arr 
         (map (fn [sq] (apply merge-with max sq)))
         (map vals)
         (map #(apply * %))
         (apply +)))

;(map #(apply merge-with + %) (read)) 
#_(defn solution-1 [arr] 
  (let [threshold { "red" 12 "green" 13 "blue" 14 }
        summed-scores (map #(apply merge-with + %) arr)] 
        (->> summed-scores 
             (map-indexed #(if (cmp-map threshold %2) 
                             (inc %1)
                             0))
             (reduce +)
             )))
(def test 
"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
 Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
 Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
 Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
 Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
#_(println (solution-1 (parse (str/split-lines test))))

(println (solution-1 (parse (input "./input2.txt"))))

(println (solution-2 (parse (input "./input2.txt"))))


