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

(def into-vec (comp read-string #(str "[" % "]" ) second))

(def non-period (comp #(str/split % #"") first))

(defn factorial
  ([n]                    ; when only one argument is passed in
    (factorial n 1))
  ([n acc]                ; when two arguments are passed in
    (if  (= n 0)  acc
    (recur (dec n) (* acc n)))))

(->> (u/input test)
     (map #(str/split % #" "))
     (map (juxt non-period into-vec))
     (map (fn [[f l]] { :pattern f :broken l :max-arrangement (factorial (count f))   })))

