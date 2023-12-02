(require '[clojure.string :as str])

(defn input [path]  
  (-> (slurp path)
      (str/split-lines)))

(defn idexof-semi[s] 
    (str/index-of s ";"))

(defn parse-pairs [sq] 
    (->> sq (map #(str/split % #" "))
            (map (fn [[k v]] [v (Integer/parseInt k)]))))

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
         (map #(apply merge-with max %))
         (map #(apply * (vals %)))
         (apply +)))


(println (solution-1 (parse (input "./input2.txt"))))

(println (solution-2 (parse (input "./input2.txt"))))


