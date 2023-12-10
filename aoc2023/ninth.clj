(:require '[clojure.java.io :as io])
(require '[utils :as u])

(def test 
"0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45
")


(defn derivations [root] 
  (loop [result [root]
         cur root]
    (if (every? #(= 0 %) cur) 
      result
      (let [next-derivation (apply vector (map #(- %2 %1) cur (rest cur)))]
       (recur (conj result next-derivation) next-derivation)))))
  
(def i (->> (u/input (io/resource "./resources/input9.txt")) 
     (map #(str "[" % "]" ))
     (map read-string)
     (map derivations)
     (map rseq)))



(let [s1 (map #(reduce + (map (fn [v] (last v)) %)) i)] 
  (println (apply + s1)))
