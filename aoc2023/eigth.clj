(require '[clojure.string :as str])
(require '[utils :as u])

(defn traverse [node-map root instructs]
  (loop [start root
         path [start]
         [i & rst] instructs] 
    (let [nxt (nth (node-map start) i)]
      (if (str/ends-with? nxt "Z")
        path
        (recur nxt (conj path nxt) (conj (apply vector rst) i))))))

(defn parse-nodemap [rst]
  (->> rst (map #(-> % (str/split #"="))) 
           (filter #(= (count %) 2))
           (map (juxt (comp read-string #(str ":" %) first) 
                      (comp read-string (fn [s] (-> s (str/trim) 
                                                      (str/replace #"\((\w+), (\w+)\)" "( :$1 :$2 )"))) second)))
           (into {})))

(defn gcd [a b] (if (zero? b) a (recur b (mod a b))))
(defn lcm [v] (reduce #(/ (* %1 %2) (gcd %1 %2)) v))

(defn solution [data] 
  (let [[fst & rst] (u/input (io/resource data))
        instructions (->> (seq fst) (map { \R 1 \L 0 }))
        node-map (parse-nodemap rst)  
        start (filter #(str/ends-with? % "A") (keys node-map))]
        (let [path (traverse node-map :AAA instructions)
              paths (map #(count (traverse node-map % instructions)) start)]
          [(count path) (lcm paths)])))

(let [[part1 part2] (solution "./resources/input8.txt")]
  (println part1 part2))

