(require '[utils :as u])

(defn lines->grid [lines] 
  (map #(apply vector %) lines))

(def test 
".....
.S-7.
.|.|.
.L-J.
.....")


(def grid (apply vector (->> (u/input test) (lines->grid))))

(defn find-2d [pred grid]
  (for [[i line] (map-indexed vector grid)
        [j ch] (map-indexed vector line)
        :when (pred ch)]
    [[i j] ch]))


(def translations [[0 -1] 
                   [1  0] 
                   [0  1]
                   [-1 0]])

(defn calc-offset [pos] 
  "Return a new array of coordinates given pos with cardinal direction translations"
  (map (fn [pr] (map + pos pr)) translations))

(let [[[pos S] & rst] (find-2d #{\S} grid)]
  (println (map #(get-in grid %) (calc-offset pos))))

