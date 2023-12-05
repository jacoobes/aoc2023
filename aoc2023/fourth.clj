(require '[clojure.string :as str])
(require '[clojure.set :as set])


(defn normalize-str [url] 
  (try (slurp url) 
       (catch Exception e 
       (do (println "Warning: non url passed") url))))

(defn input [url]  
  (-> (normalize-str url)
      (str/replace #"Card +\d+:" "")
      (str/split-lines)))

(defn sumnum [nums] 
  (apply + nums))

(defn split-bar [s] 
  (str/split s #"\|"))

(defn create-set-pairs [[f l]] 
  [(read-string (str "#{" f "}" )) 
   (read-string (str "#{" l "}")) ])

;math to calculate card score  geometric progression
;   8 = 2^4 / 2
;   1 = 2^1 / 2
;   0 = 2^0 / 2  = 1 // edge case?

(def test "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
           Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
           Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
           Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
           Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
           Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")
(defn create-winners [v] 
  (->> v
      (map #(split-bar %))
      (mapv create-set-pairs)
      (map #(apply set/intersection %))))

(defn solution1 [input]
  (->> (create-winners input)
       (map #(if (empty? %) 
                      0
                      (/ (Math/pow 2 (count %)) 2)))
       sumnum))

(defn solution2 [t]
   (->> (create-winners t) 
       (map count)))

(println (solution1 (input "./input4.txt")))

(def res (solution2 (input test)))

(def vecres 
  (into [] (map-indexed #(range (+ % 2) (+ %2 % 2)) res)))


(defn solve-card [i] 
  (fn [acc v] 
   (update acc v + (acc i 1))))

(def occurences 
  (loop [[[i h] & rst] (map-indexed vector res)
          sum []]
    (if h
      (let [start (zipmap (range 1 (count res)) (repeat 1))
            _ (println vecres)]
       (recur rst (conj sum (reduce (solve-card i) start (get vecres i)) )))
      sum))) 

(println res)


(println (apply + (flatten (map vals occurences))))
