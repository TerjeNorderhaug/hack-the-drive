(ns hack-the-drive.view.grid
  (:require [net.cgrand.enlive-html :as html]))
  
(html/deftemplate grid-view "grid.html"
  [items cars]
  [:.datapoint]
  (html/clone-for [{img :img vid :vid timestamp :timestamp 
                  d1 :isDriverRearOpen d2 :isDriverFrontOpen
                  d3 :isPassengerRearOpen d4 :isPassengerFrontOpen
                  w1 :isPassengerOpen w2 :isDriverOpen
                  intensity :intensity} items]
     [:img] (html/set-attr :src img)
     [:.vehicle] (html/content vid)
     [:.timestamp] (html/content timestamp)
     [:.doors] (html/content (if (or d1 d2 d3 d4) "open" "closed"))
     [:.windows] (html/content (if (or w1 w2) "open" "closed"))
     [:.bar] 
        (html/clone-for [bar (take 8 (reverse intensity))]
          (html/set-attr :style (str "width: " (float (/ (second bar) (* 64 48) 1/2)) "%"))))
  [:.capture :.vehicle]
  (html/clone-for [{vin :vin} cars]
     (html/content vin)))

