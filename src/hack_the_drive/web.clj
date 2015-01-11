(ns hack-the-drive.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [ring.middleware [multipart-params :as mp]]
            [ring.middleware.multipart-params.byte-array :refer [byte-array-store]]
            [ring.util.response :as resp]
            [monger.util :refer [get-id]]
            [environ.core :refer [env]]
            [net.cgrand.enlive-html :refer [deftemplate]]
            [hack-the-drive.storage :refer [store-media retrieve-media]]
            [hack-the-drive.capture :refer [capture-vehicle]]))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str ["Hello" :from 'Heroku])})

(defn render [t]
  (apply str t))

(deftemplate index "capture.html" [])

(deftemplate upload-success "success.html" [])

(defn render-media-response [record]
    (-> (resp/response (new java.io.ByteArrayInputStream (:bytes record))) ; (java.io.ByteArrayInputStream. 
        (resp/content-type (:content-type record))
        (resp/header "Content-Length" (count (:bytes record)))))

; (count (retrieve-data "test"))
; (str (:_id (first (retrieve-data "media"))))

; (:bytes (retrieve-media "54b1fab703642f60fb7a441a"))
; (render-media-response (retrieve-media "54b1fab703642f60fb7a441a"))

(defroutes app
  (GET  "/" [] (render (index)))
  (mp/wrap-multipart-params
    (POST "/capture" {params :params} 
        (let [id (store-media (get params "file"))]
          ; (render (upload-success))
          (resp/redirect (clojure.string/replace "/media/:id" #":id" (str id)))))
    {:store (byte-array-store)})
  (GET "/media/:id" [id]
    (render-media-response 
     (retrieve-media id)))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
