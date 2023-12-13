(:require '[clojure.string :as str])
(:require '[clojure.java.io :as io])
(:require [clojure.math.combinatorics :as combo])
(require '[utils :as u])

(def test 
"???.### 1,1,3
.??..??...?##. 1,1,3
?#?#?#?#?#?#?#? 1,3,1,6
????.#...#... 4,1,1
????.######..#####. 1,6,5
?###???????? 3,2,1")

(def into-vec 
  (comp read-string #(str "[" % "]" ) second))
;#"\?|\#+|\."
(def non-period 
  (comp #(str/split % #"") first))

(defn factorial
  ([n] (factorial n 1)); when only one argument is passed in
  ([n acc]                ; when two arguments are passed in
    (if  (= n 0)  acc
    (recur (dec n) (* acc n)))))


(def v (->> (u/input test)
     (map #(str/split % #" "))
     (map (juxt non-period into-vec))
     (map (fn [[f l]] { :placements f 
                        :broken l 
       ;                 :fac-length (factorial (count f)) 
                        :sum-broken (count (filter #(= \# %) f) )
                        :broken-freqs (frequencies l) }))))

(let [mp (first v)
      { placements :placements } mp] 
     
  )
