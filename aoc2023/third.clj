

(defn input [url]  
  (-> (slurp url)
      (str/split-lines)))

(defn period? [c] 
  (= c \.))

#_"
  known=
    lines are even length
    . = no symbol
    anything else is symbol
"

(defn solution1 [url] 
  (let [grid (map seq (input url))]
    grid))

(println (solution1 "./input3.txt"))
