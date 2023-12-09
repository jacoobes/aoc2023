(:require '[clojure.java.io :as io])
#_ "--- Day 1: Trebuchet?! ---

Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.

You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.

Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

You try to ask why they can't just use a weather machine (\"not powerful enough\") and where they're even sending you (\"the sky\") and why your map looks mostly blank (\"you sure ask a lot of questions\") and hang on did you just say the sky (\"of course, where do you think snow comes from\") when you realize that the Elves are already loading you into a trebuchet (\"please hold still, we need to strap you in\").

As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are having trouble reading the values on the document.

The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet

In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.

Consider your entire calibration document. What is the sum of all of the calibration values?"


(defn input [path]  
  (-> (slurp path)
      (str/split-lines)))

(defn solution-part-1 [in] 
    (->> in (map #(str/replace % #"[A-Za-z\s]+" ""))
            (map #(str (first %) (last %)))
            (map #(Integer/parseInt %))
            (apply +)))

(println (solution-part-1 (input (io/resource "./resources/input1.txt"))))

;one, two, three, four, five, six, seven, eight, and nine
(def wordnums { 
  "one" "1"                   
  "two" "2"                   
  "three" "3"                   
  "four" "4"                  
  "five" "5"                 
  "six" "6"                  
  "seven" "7"                   
  "eight" "8"                  
  "nine" "9"                  
})


(defn word [charseq]
  (re-seq #"one|two|three|four|five|six|seven|eight|nine|\d+" charseq))

(defn is-numeric? [s] (re-matches #"\d+" s))

(defn solution-part-2 [in] 
  (->> in 
      ;;Credit to @SrIzan for showing these edge cases
      (map #(str/replace % #"twone" "21"))
      (map #(str/replace % #"sevenine" "79"))
      (map #(str/replace % #"oneight" "18"))
      (map #(str/replace % #"threeight" "38"))
      (map #(str/replace % #"nineight" "98"))
      (map #(str/replace % #"fiveight" "58"))
      (map #(str/replace % #"eighthree" "83"))
      (map #(str/replace % #"eightwo" "82"))
      (map word)
      (map (fn [v] [(first v) (last v)]))
      (map (fn [[f l]] [(if-let [d (wordnums f)] d (first f))
                        (if-let [d (wordnums l)] d (last l))]))
      (map (fn [v] 
             (Integer/parseInt (apply str v))))
      (apply +)))

; 53894
(println (solution-part-2 (input (io/resource "./resources/input1.txt"))))


