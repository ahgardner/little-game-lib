(ns little-game-lib.undo)

; Klondike solitaire (character mode) - undo feature for a map

(def undo-key :little-game-lib-undo)
(def prev-key :little-game-lib-undo-prev)

(defn undo [m]
  (let [p (prev-key m)]
    (if p (assoc p undo-key true) p)))

(defn chain [this last]
  (if (= this last)
    this
    (if (undo-key this)
      (dissoc this undo-key)
      (assoc this prev-key last))))