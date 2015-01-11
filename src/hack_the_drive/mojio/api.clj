(ns  hack_the_drive.mojio.api
  (require 
    [clj-http.client :as client]
    [cheshire.core :as json]
    [environ.core :refer [env]]))

;; http://data.hackthedrive.com/reference/documentation

(def base-uri "https://data.api.hackthedrive.com/v1/")
(def insecure-ssl true)

(def app-id (env :mojio-app-id))
(def app-secret (env :mojio-app-secret))

(def secret-api-token (env :mojio-api-token))

(defn endpoint [path]
  (str base-uri path))

(defn get-raw [path & [params]]
  (-> (endpoint path)
      (client/get
       {:headers {"Content-Type" "text/json"
                  "MojioAPIToken" secret-api-token}
        :insecure? insecure-ssl
        :query-params params})))

(defn get-json [path & [params]]
  (-> (get-raw path params)
      :body
      (json/parse-string true)))

; (get-json "Apps" {})

(defn get-data
  "Paginated retrival of api data"
  ([path & [params]]
   (let [step  (get params :limit 10)]
    (apply concat
      (for [i (range)
            :let [offset (* i step)
                  chunk (get-json path (assoc params :offset offset :limit step))]
            :while (and (not (empty? chunk)) 
                        (< offset (get chunk :TotalRows 0)))]
            (:Data chunk))))))

; (get-data "Apps" {})

(defn delete [path]
  (client/delete path
       {:headers {"MojioAPIToken" secret-api-token}}))

(defn post [path body]
   (client/post path
       {:headers {"MojioAPIToken" secret-api-token}
        :body (json/generate-string body)}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; APPS

(defn all-apps [& {:keys [sort-by desc] :as params}]
  (get-data "Apps" params))

; (all-apps)

;; ## incomplete
(defn create-app 
  "Create a new application (restricted function)"
  ([] nil))

;; ## incomplete
(defn update-app
  "Update an existing application"
   ([] nil))

;; ## incomplete
(defn delete-app
  "remove an existing application from the system"
   ([] nil))

(defn get-app 
  "Details about an application"
  ([id] (get-json (clojure.string/replace "Apps/:id" #":id" id))))

(defn delete-admin 
  "Remove an administrator from an app"
  ([id user-id]
    (delete (clojure.string/replace "Apps/:id/Admin" #":id" id))))

(defn add-admin
  "Add an administrator to an application"
  ([id user-id]
    (post (clojure.string/replace "Apps/:id" #":id" id) user-id)))

;;; ETC - many more!

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EVENTS

(defn all-events [& {:keys [sort-by desc criteria] :as params}]
  (get-data "Events" params))

; (all-events)

;;; ETC - many more!


  


   


    



 









