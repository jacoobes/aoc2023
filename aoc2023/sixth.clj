(ns sixth
  (:require [clojure.string :as str]
            [utils :as u]))

(def test-input 
  "Time:      7  15   30
   Distance:  9  40  200")


(defn after-colon [st] 
  (inc (str/index-of st ":" 4)))
;Your toy boat has a starting speed of zero millimeters per millisecond. 
;For each whole millisecond you spend at the beginning of the race holding down the button,
;the boat's speed increases by one millimeter per millisecond.
; holding button for x 
; goes for y
; hold for 0, goes for 0
; holds for 1, goes for 


(defn rem-time [time hold] 
  (- time hold))

(defn distance [hold time]
  (* hold (rem-time time hold)))

(defn get-data [url, transform-str] 
  ( ->> (u/input url) 
        (map #(subs % (after-colon %)))
        (map #(read-string (transform-str %)))))

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

(defn solution1 [input] 
  (let [matrix (transpose (get-data input transform1))]
      (apply * (map count (reduce calc-distances [] matrix)))))

(defn solution2 [input] 
   (let [matrix (transpose (get-data input transform2))]
      (apply * (map count (reduce calc-distances [] matrix)))))
(defn -main [ ]
  (let [s1 (solution1 "./aoc2023/input6.txt")
        s2 (solution2 "./aoc2023/input6.txt")] 
   (do (println s1)
       (println s2))))


