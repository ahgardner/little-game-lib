(ns little-game-lib.core-test
  (:require [clojure.test :refer :all]
            [little-game-lib.undo :refer :all]
            [little-game-lib.time :refer :all]
            [clojure.java.io :as io]))

(def cfgfile "/Users/dad/little-game-lib/test/test-file.cfg")

(deftest test-app
         
  (testing "undo"
    (let [p1 {:base "X"}
          p2 (assoc p1 :something 1)
          p3 (chain p2 p1)
          p4 (undo p3)
          p5 (chain p4 p3)
          p6 (undo p5)]
      (is (and (= p5 p1) (nil? p6)))))
         
  (testing "time"
    (when (not (.exists (io/as-file cfgfile))) (spit cfgfile "best-time=10000"))
    (let [state1 {}
          state2 (start state1)
          bt (best-time! state2 cfgfile)
          this-time (get-time-diff state2)]
      ;(io/delete-file cfgfile)
      (is (<= bt this-time)))))
