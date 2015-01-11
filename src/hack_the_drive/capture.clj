(ns hack-the-drive.capture
  (:import [com.mongodb MongoOptions ServerAddress])
  (:require [monger.core :as mg]
            [environ.core :refer [env]]))

; (defn mongolab-uri (env :MONGOLAB_URI))

