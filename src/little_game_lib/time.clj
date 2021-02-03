(ns little-game-lib.time (:require [clojure.java.io :as io] 
                             [java-time :as t]
                              [clojure.string :as str]))

; Track best time for a game, which passes its state as a map

(def start-time-key :little-game-lib-start-time)

(defn- get-time [pref-file]
  (let [file-exists (.exists (io/as-file pref-file))]
    (if file-exists
      (let [hsstr (slurp pref-file)]
        (read-string (second (str/split hsstr #"="))))
      10000)))

(defn- set-time! [new-time pref-file]
      (spit pref-file (str "best-time=" new-time)))

(defn- diff [old-time new-time] 
  (t/time-between old-time new-time :seconds))

(defn start [m] (assoc m start-time-key (t/instant)))

(defn get-time-diff [m]
  (diff (start-time-key m) (t/instant)))
    
(defn best-time! [m pref-file] 
  (let [start-time (start-time-key m)
        new-time-seconds (diff start-time (t/instant))
        old-time-seconds (get-time pref-file)]
    (when (< new-time-seconds old-time-seconds) (set-time! new-time-seconds pref-file))
    (min new-time-seconds old-time-seconds)))


  
