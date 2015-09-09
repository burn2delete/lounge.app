(ns lounge.app
  (:require [lounge.lang.sets :as sets])
  (:require-macros [javelin.core :refer [defc defc= cell=]]))

(defc env [])

(defc= app env (partial reset! env))
;the following cell prints changes to the js console when app is modified
(cell= (println app))

(defn comp-names [ent]
  (into #{} (map #(keyword (:name %)) (second ent))))

(defn contains-comp? [ent & filts]
  (sets/intersects? (into #{} filts) (comp-names ent)))

(defn get-ent [env ename]
  (first (filterv #(= ename (first %)) env)))

(defn app-by-ent [& filts]
  (cell= (filterv #(contains? (into #{} filts) (first %)) app)))

(defn get-comp [[ename ecomps] cname]
  (first (filterv #(= cname (keyword (:name %))) ecomps)))

(defn get-by-comp [env & filts]
  (filterv #(apply contains-comp? % filts) env))

(defn app-by-comp [& filts]
  (cell= (apply get-by-comp app filts)))

(defn get-in-all [env cname pname]
  (concat (mapv #(get (get-comp % cname) pname nil) env)))
