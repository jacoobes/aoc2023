(:require '[clojure.string :as str])
(:require '[clojure.pprint :as pretty])

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


#_(defn transform-char [i c] 
  (cond  (period? c) -1
         (digit? c) 
         :else i))

(defn parse-seq 
  [strseq] )

(defn get-position [tpk] 
    (->> tpk 
         (filter #(and (number? %) (not= -1 %))) 
         (map int)))

(defn lines->grid [lines] 
  (map #(apply vector %) lines))

(defn solution1 [lines] 
     (lines->grid lines)   
  )

(println (solution1 (input "./input3.txt")))
