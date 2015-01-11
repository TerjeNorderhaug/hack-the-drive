(ns hack-the-drive.neural
  (:use [enclog nnets training]))

(def net  
    (network  (neural-pattern :feed-forward) 
               :activation :sigmoid 
               :input   2
               :output  1
               :hidden [2])) ;;a single hidden layer 