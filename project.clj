(defproject little-game-lib "1.0.3"
  :description "Little game library exercise"
  :url "https://github.com/ahgardner/little-game-lib"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clojure.java-time "0.3.2"]
                 [org.threeten/threeten-extra "1.5.0"]]
  :deploy-repositories [["clojars" {:sign-releases false}]])
