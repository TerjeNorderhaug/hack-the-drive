(ns hack-the-drive.thermal
  (:import ;[java.io.File] 
           ;[java.awt.BufferedImage] 
           [javax.imageio ImageIO]
           [java.awt Color])
  (:require [hack-the-drive.storage :as storage]))

(defn dimensions [image]
  [(.getWidth image) (.getHeight image)])

(defn pixel-rgb [image x y]
  (let [color (Color. (.getRGB image x y))
        red (.getRed color)
        green (.getGreen color)
        blue (.getBlue color)]
    [red green blue]))

(defn pixel-hsb [image x y]
  (let [color (Color. (.getRGB image x y))
        red (.getRed color)
        green (.getGreen color)
        blue (.getBlue color)]
     (seq (Color/RGBtoHSB red green blue nil))))

(defn thermal-intensity [image granularity]
  (let [[w h] (dimensions image)]
    (->>
     (apply concat
      (for [y (range h)]
       (for [x (range w)]
         (let [[h s b] (pixel-hsb image x y)]
            (Math/round (* b granularity))))))
     (frequencies)
     (sort-by first))))

(defn image-from-bytes [bytes]
  (ImageIO/read (new java.io.ByteArrayInputStream bytes)))

(defn retrieve-image [id]
   (let [image (storage/retrieve-media "54b289530364b902b4b7bf77")]
     (image-from-bytes (:bytes image))))
     
; (thermal-intensity (retrieve-image "54b289530364b902b4b7bf77") 20)              

; (pixel-rgb image 100 100)
; (pixel-hsb image 100 100)