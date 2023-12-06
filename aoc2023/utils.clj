(ns utils 
  (:require [clojure.string :as str]))

(defn normalize-str [url] 
  (try (slurp url) 
       (catch Exception e 
       (do (println "Warning: non url passed") url))))

(defn input [url]  
  (-> (normalize-str url)
      (str/split-lines)))


