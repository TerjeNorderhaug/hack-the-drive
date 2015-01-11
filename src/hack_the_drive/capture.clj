(ns hack-the-drive.capture
  (:require [hack-the-drive.storage :as storage]
            [hack-the-drive.api.bmwcar :as bmw]))

(def api-fns [
 bmw/vehicle 
 bmw/battery
 bmw/fuel
 bmw/door
 bmw/window
 bmw/trunk
 bmw/odometer
 bmw/location
 bmw/last-trip])

(defn vehicle-details [vin]
  (apply merge (map deref (map #(future (try (% vin) (catch Exception e {:error (pr-str e)}))) api-fns))))

(defn vehicles []
  (for [v (bmw/all-vehicles)]
    (let [d (vehicle-details (:vin v))]
      (merge v d))))

; (vehicle-details "WBY1Z4C55EV273078")
; (vehicle-details "WBY1Z4C53EV273080")
; (vehicle-details "WBY1Z4C51EV275894")
; (vehicle-details "WBY1Z4C58EV275200")
; (map vehicle-details (map :vin (bmw/all-vehicles)))

; (vehicles)

(defn capture-vehicle [id]
  (let [data (vehicle-details id)]
    (storage/insert-data "vehicle-status" data)
    data))

; (capture-vehicle "WBY1Z4C55EV273078")

; (count (storage/retrieve-data "vehicle-status"))

(defn -main [& [id]]
  (if-not id
    (doseq [id (map :vin (bmw/all-vehicles))]
      (capture-vehicle id))
    (capture-vehicle id)))
