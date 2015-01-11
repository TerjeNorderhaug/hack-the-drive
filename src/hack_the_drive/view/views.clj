(ns hack-the-drive.view.views
  (:require [net.cgrand.enlive-html :refer [deftemplate]]))
  
(deftemplate index-view "capture.html" [])

(deftemplate success-view "success.html" [])

(deftemplate watch-view "watch.html" [])
