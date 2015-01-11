(ns hack-the-drive.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [ring.middleware [multipart-params :as mp]]
            [environ.core :refer [env]]
            [net.cgrand.enlive-html :refer [deftemplate]]))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str ["Hello" :from 'Heroku])})

(defn render [t]
  (apply str t))

(deftemplate index "capture.html" [])

(deftemplate upload-success "success.html" [])

(defn upload-file [file]
  ; (ds/copy (file :tempfile) (ds/file-str "file.out"))
  (render (upload-success)))

(defroutes app
  (GET  "/" [] (render (index)))
  (mp/wrap-multipart-params
    (POST "/capture" {params :params} 
        (upload-file (get params "file"))))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
