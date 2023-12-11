(require '[utils :as u])

(defn lines->grid [lines] 
  (map #(apply vector %) lines))



(def galaxy
"...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#.....")

(def grid (apply vector (->> (u/input galaxy) 
                             (lines->grid))))

(defn col [idx grid] 
  (into [] (->> (range 0 (count grid))
                (map #(get-in grid [% idx])))))

(defn row [idx grid]
  (grid idx))

(defn no-galaxies? [vect]
  (every? #(= \. %) vect))

(defn find-2d [pred grid]
  (for [[i line] (map-indexed vector grid)
        [j ch] (map-indexed vector line)
        :when (pred ch)]
    [[i j] ch]))


(for [[i line] (map-indexed vector grid)]
  (let [cl (col i grid)] 
    (if (no-galaxies? line)
      (if (no-galaxies? cl)
        [(update cl i #(vector %))
         (update cl i #(vector %))]
        [line line])
      (if (no-galaxies? cl)
        (update cl i #(vector %))
        line))
    ))

