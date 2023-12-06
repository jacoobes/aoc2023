(ns sixth
  (:require [clojure.string :as str]
            [utils :as u]))

(def test-input 
  "Time:      7  15   30
   Distance:  9  40  200")

(defn after-colon [st] 
  (inc (str/index-of st ":" 4)))

(defn distance [hold time]
  (* hold (- time hold)))

(defn get-data [url transform-str] 
  ( ->> (u/input url) 
        (map #(subs % (after-colon %)))
        (map #(-> % transform-str read-string))))

(defn transform1 [s] 
  (str "[" s "]"))

(defn transform2 [s] 
  (str "[" (str/replace s #" +" "") "]"))

(defn transpose [matrix] 
  (apply map vector matrix))

(defn possible-distances [t]
  (map #(distance % t) (range 1 t)))

(defn keep-threshold [distances d] 
  (filter #(> % d) distances))

(defn calc-distances [acc [t d]]
 (let [distances (possible-distances t)]
  (conj acc (keep-threshold distances d))))

(defn solution [input transform] 
  (let [matrix (transpose (get-data input transform))
        _ (println matrix) ]
      (apply * (map count (reduce calc-distances [] matrix)))))

(defn -main [ ]
  (let [s1 (solution "./aoc2023/input6.txt" transform1)
        s1 (solution test-input transform1)
        #__s2 #_(solution2 "./aoc2023/input6.txt")] 
   (do (println s1)
       #_(println s2))))


