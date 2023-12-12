(:require '[clojure.string :as str])
(:require '[clojure.java.io :as io])
(require '[utils :as u])

(def test 
"???.### 1,1,3
.??..??...?##. 1,1,3
?#?#?#?#?#?#?#? 1,3,1,6
????.#...#... 4,1,1
????.######..#####. 1,6,5
?###???????? 3,2,1")

(def into-vec (comp read-string #(str "[" % "]" ) second))

(def non-period (comp #_#(re-seq #"[\?|\#]+" %) first))

(->> (u/input test)
     (map #(str/split % #" "))
     (map (juxt non-period into-vec))
     (map (fn [[f l]] { :pattern f :broken l }))
     )
