(ns show-led.core
  (:require [clojure.string :refer [split-lines]]
            [clojure.pprint :refer [pprint]]
            )
  (:gen-class))

(def leds {"0" [" ____ " "|    |" "|    |" "|    |" "|____|"]
           "1" ["      " "     |" "     |" "     |" "     |"]
           "2" [" ____ " "     |" " ____|" "|     " "|____ "]
           "3" [" ____ " "     |" " ____|" "     |" " ____|"]
           "4" ["      " "|    |" "|____|" "     |" "     |"]
           "5" [" ____ " "|     " "|____ " "     |" " ____|"]
           "6" [" ____ " "|     " "|____ " "|    |" "|____|"]
           "7" [" ____ " "     |" "     |" "     |" "     |"]
           "8" [" ____ " "|    |" "|____|" "|    |" "|____|"]
           "9" [" ____ " "|    |" "|____|" "     |" "     |"]
           ":" ["      " "      " "   ·  " "   ·  " "      "]
           "." ["      " "      " "      " "      " "   .  "]})

(defn show-led
  [leds]
  (->> leds
    (apply interleave)
    (partition (count leds))
    (map (partial interpose " "))
    (mapcat #(conj (vec %) "\n"))
    (apply str)))

(defn -main
  [& [number]]
  (let [n (or number "1978")
        output (show-led (map (comp leds str) n))]
    (doseq [line (split-lines output)]
      (println line))))
