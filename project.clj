(defproject mojio-clj "0.1.0-SNAPSHOT"
  :description "BMW Hack The Drive"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.5"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [environ "0.5.0"]
		 [clj-http "1.0.0"]	  
                 [cheshire "5.3.1"]
                 [enlive "1.1.1"]
                 [hiccup "1.0.3"]]
  :plugins [[lein-ring "0.8.3"]
            [lein-swank "1.4.5"]
  	    [environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :min-lein-version "2.0.0"
)
