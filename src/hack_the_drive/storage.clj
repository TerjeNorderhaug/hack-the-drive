(ns hack-the-drive.storage
  (:import [com.mongodb MongoOptions ServerAddress])
  (:import [org.bson.types ObjectId])
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.conversion :refer [from-db-object]]
            [environ.core :refer [env]]))

(def mongolab-uri (env :mongolab-uri))

; (def conn (mg/connect-via-uri  mongolab-uri))

; (mg/disconnect conn)

(defn insert-data [collection value]
  (let [{conn :conn db :db} (mg/connect-via-uri mongolab-uri)
        result (mc/insert-and-return db collection value)]
    (mg/disconnect conn)
    result))

;; (insert-data "docs2" {:name "John" :age 30})

(defn retrieve-data [collection & [match]]
  (let [{conn :conn db :db} (mg/connect-via-uri mongolab-uri)
        object (mc/find-maps db collection (or match {}))
        result (from-db-object object true)]
    (mg/disconnect conn)
    result))

(defn retrieve-one [collection & [match]]
  (let [{conn :conn db :db} (mg/connect-via-uri mongolab-uri)
        object (mc/find-one db collection (or match {}))
        result (from-db-object object true)]
    (mg/disconnect conn)
    result))

(defn delete-one [coll id]
  (let [{conn :conn db :db} (mg/connect-via-uri mongolab-uri)]
    (mc/remove-by-id db coll (ObjectId. id))))

;; (retrieve-data "docs2" {:name "John"})

(defn store-media [object]
  ; (ds/copy (file :tempfile) (ds/file-str "file.out"))
  (:_id (insert-data "media" object)))

(defn retrieve-media [id]
  (retrieve-one "media" {:_id (ObjectId. id)}))

(defn all-media []
  (retrieve-data "media"))

; (all-media)
; (delete-one "media" "54b2dd8be4b0f7955369d24e")

