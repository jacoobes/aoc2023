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

                   ;x  y
(def translations [[0 -1]   ;n
                   [-1  0]   ;w
                   [0  1]   ;e
                   [1 0]]) ;s

(defn surrounding [pos] 
  "Return a new array of coordinates given pos with cardinal direction translations"
  (map (fn [pr] (map + pos pr)) translations))

;x -> y
(def directions { \| [:n :s]  
                  \_ [:e :w]
                  \L [:n :e]
                  \J [:n :w]
                  \7 [:s :w]
                  \F [:s :e] })


(let [[[pos cur] & rst] (find-2d #{\S} grid)]
  (loop [offsets (surrounding pos)
         [n w e s] (->> offsets
                   (map (partial get-in grid)))
         done false
         distance 0]
      (cond 
        (and done (= \S cur)) distance
        (= \S cur)
        
        ))
  )

