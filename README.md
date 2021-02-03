# little-game-lib

A Clojure library designed for simple games (like character based)

## Usage

state is any map

undo - keep a stack of states and pop from it

   (chain state previous-state) returns state  
   (undo state) returns state (nul if cannot undo)

SAMPLES:

 (defn -main
   [& args]
   (help)
   (loop [table (deal)]
     (display/show table)
     (let [newtable (do-command table)]
       (when newtable (recur (undo/chain newtable table))))))

 (defn- undo [table]
   (let [prev (undo/undo table)]
     (if prev
       prev
       (com/beep table "Can't undo before doing anything"))))


time - keep best time

   (start state) starts the clock  
   (best-time! state pref-file) returns best time in seconds 
   (get-time-diff state) returns time elapsed in seconds                           

   SAMPLE:
    (let [best-time (tm/best-time! table pref-file)] 
      (display/show table)
      (println (str "Your time " (tm/get-time-diff table) "s. Best time " best-time "s.")))

## Changes

Modified to use clojure.java-time Feb. 2021

## License

Copyright © 2017 Arthur Gardner

Distributed under the Eclipse Public License version 1.0.
