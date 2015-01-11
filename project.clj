(defproject hack-the-drive "0.1.0-SNAPSHOT"
  :description "BMW Hack The Drive"
  :url "http://docs.hackthedrive.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [ring "1.3.1"]
                 [environ "0.5.0"]
		 [clj-http "1.0.0"]	  
                 [cheshire "5.3.1"]
                 [enlive "1.1.1"]
                 [hiccup "1.0.3"]
                 [com.novemberain/monger "2.0.0"]]
  :plugins [[lein-ring "0.8.3"]
            [lein-swank "1.4.5"]
  	    [environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :min-lein-version "2.0.0"
  :uberjar-name "hack-the-drive.jar"
  :ring {:handler hack-the-drive.web/app}
  :profiles {:production {:env {:production true}}}
)

