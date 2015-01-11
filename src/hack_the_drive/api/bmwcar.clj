(ns hack-the-drive.api.bmwcar
  (require 
    [clj-http.client :as client]
    [cheshire.core :as json]))

(def base-uri "http://api.hackthedrive.com/vehicles/")

(defn endpoint [path]
  (str base-uri path))

(defn get-raw [path & [params]]
  (-> (endpoint path)
      (client/get
       {:headers {"Content-Type" "text/json"}
        :query-params params})))

(defn get-json [path & [params]]
  (-> (get-raw path params)
      :body
      (json/parse-string true)))

; (get-json "" {})

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

(defn post-json [path body]
   (client/post (endpoint path)
       {:headers {"Content-Type" "application/json"}
        :body (json/generate-string body)}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn get-vin-json [pattern vin]
  (get-json (clojure.string/replace pattern #":vin" vin)))

(defn post-vin-json [pattern vin payload]
  (-> (post-json (clojure.string/replace pattern #":vin" vin) payload)
      :body
      (json/parse-string true)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; VEHICLES

;; ## fix

(defn all-vehicles [& {:keys [] :as params}]
  (get-data "" params))

; (all-vehicles)

(defn vehicle [vin]
  (get-vin-json ":vin/" vin))

; (vehicle "WBY1Z4C55EV273078")

(defn battery [vin]
  (get-vin-json ":vin/battery/" vin))

; (battery "WBY1Z4C55EV273078")

(defn fuel [vin]
  (get-vin-json ":vin/fuel/" vin))

; (fuel "WBY1Z4C55EV273078")

(defn door [vin]
  (get-vin-json ":vin/door/" vin))

; (door "WBY1Z4C55EV273078")

(defn window [vin]
  (get-vin-json ":vin/window/" vin))

; (window "WBY1Z4C55EV273078")

(defn trunk [vin]
  (get-vin-json  ":vin/trunk/" vin))

; (trunk "WBY1Z4C55EV273078")

(defn odometer [vin]
  (get-vin-json ":vin/odometer/" vin))

; (odometer "WBY1Z4C55EV273078")

(defn location [vin]
  (get-vin-json ":vin/location/" vin))

; (odometer "WBY1Z4C55EV273078")

(defn last-trip [vin]
  (get-vin-json ":vin/lastTrip/" vin))

; (last-trip "WBY1Z4C55EV273078")

(defn send-address [vin payload]
  (post-vin-json ":vin/navigation/" vin payload))

; (send-address "WBY1Z4C55EV273078" {"label" "Navdy HQ" "lat" 37.773550 "lon" -122.403309})

(defn headlights [vin {key :key :as payload}]
  (post-vin-json ":vin/lights/" vin payload))

; (headlights "WBY1Z4C55EV273078" {:key "abcd"})

(defn lock [vin {key :key :as payload}]
  (post-vin-json ":vin/lock/" vin payload))

; (lock "WBY1Z4C55EV273078" {:key "abcd"})

(defn horn [vin {key :key :as payload}]
  (post-vin-json ":vin/horn/" vin payload))

; (horn "WBY1Z4C55EV273078" {:key "abcd"})
























  


   


    



 









