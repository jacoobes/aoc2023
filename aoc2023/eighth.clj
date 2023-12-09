(ns eigth
  (:require [utils :as u]
            [clojure.string :as str]))

(defn traverse [node-map root instructs]
  (loop [start root
         path [start]
         [i & rst] instructs] 
    (let [nxt (nth (node-map start) i)]
      (if (or (= nxt nil) (= nxt 'ZZZ))
        path
        (recur nxt (conj path nxt) (conj (apply vector rst) i))))))

(defn solution [data] 
  (let [[fst & rst] (u/input data)
        instructions (->> (seq fst) (map #(if (= \R %) 1 0)))
        node-map (->> rst (map #(-> % (str/replace #"," "") 
                                      (str/split #"="))) 
                          (filter #(= (count %) 2))
                          (map (juxt (comp read-string first) (comp read-string second)))
                          (into {}))
        start (-> node-map first first)]
        (let [path (traverse node-map 'AAA instructions)]
          (count path))))

(def test 
"LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)")

(println (solution "./aoc2023/input8.txt"))
