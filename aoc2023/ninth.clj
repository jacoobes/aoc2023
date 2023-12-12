(:require '[clojure.java.io :as io])
(require '[utils :as u])

(defn derivations [root] 
  "Recursively generating derivations by juxtaposing the current sequence by its remainder and subtracting"
  (loop [result [root]
         cur root]
    (if (every? #(= 0 %) cur) 
      result
      (let [next-derivation (apply vector (map (u/flip -) cur (rest cur)))]
       (recur (conj result next-derivation) next-derivation)))))
  
(defn parse-input [data]
  (->> (u/input data) 
       (map (comp read-string #(str "[" % "]" )))))

(def input9 (->> (parse-input (io/resource "./resources/input9.txt"))
                 (map derivations)
                 (map rseq)))

(let [s1 (map #(reduce + (map last %)) input9)
      _ (println (apply + (map (comp last identity) input9)))
      s2 (map #(reduce (u/flip -) (map first %)) input9)] 
  (println (apply + s1) (apply + s2)))

