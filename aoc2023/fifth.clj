(ns fifth 
  (:require [clojure.string :as str]))


(def test 
"seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4")

(defn normalize-str [url] 
  (try (slurp url) 
       (catch Exception e 
       (do (println "Warning: non url passed") url))))

(defn input [url]  
  (-> (normalize-str url)
      (str/split-lines)))

(def mapstrings ( ->> (input "./aoc2023/input5.txt")
                      (map #(str/split % #":"))))


(def seeds (read-string 
             (str "[" (-> mapstrings first second) "]"))) 

#_(println (rest mapstrings))

(def remv (->> (rest mapstrings)
               flatten
               (map str/trim)
               (partition-by str/blank?)
               (filter #(> (count %) 1))))

#_(println remv)

(defn parse-range [rng] 
  (let [rng (read-string (str "[" rng "]"))
        [dest src amt] rng]
       [src dest amt]))


(def ranges (->> remv 
               (map rest)
               (map #(map parse-range %))))

(defn in-ranges [seed rngs] 
  (reduce (fn [sd [src dest amt]] 
               (let [#_ (println (+ sd src (- dest)))] 
            (if (and (<= src sd) (< sd (+ src amt))) ; in range of [src, (src + amt))
              (+ (- sd src) dest)
              sd))) seed rngs))

(def reduced (for [seed seeds] 
  (reduce in-ranges seed ranges)))

(def answer (apply min reduced) )



(defn -main [& args] 
  (prn answer)) 
