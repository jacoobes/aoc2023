(require '[clojure.string :as str])
(require '[clojure.set :as s])

(defn input [url]  
  (-> (slurp url)
      (str/replace #"Card +\d+:" "")
      (str/split-lines)))

;math to calculate card score  geometric progression
;   8 = 2^4 / 2
;   1 = 2^1 / 2
;   0 = 2^0 / 2  = 1 // edge case?

(defn solution1 [input]
  (->> input
       (map #(str/split % #"\|"))
       (mapv (fn [[f l]]  
            [(read-string (str "#{" f "}" )) 
             (read-string (str "#{" l "}")) ]))
       (map #(apply s/intersection %))
       (map #(if (empty? %) 
                      0
                      (/ (Math/pow 2 (count %)) 2)))
              (reduce +)))

(println (solution1 (input "./input4.txt")))


