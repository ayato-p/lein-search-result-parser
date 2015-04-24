(ns lein-search-result-parser.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [instaparse.core :as insta]
            [version-clj.core :as vc]))

(def parser
  (insta/parser (io/resource "lein_search_result_parser/search_result.bnf")))

(defmacro expand-home-path [path]
  `(if (.startsWith ~path "~")
     (str/replace-first ~path #"\~" (System/getProperty "user.home"))
     ~path))

(defn parse [path]
  (with-open [r (io/reader (expand-home-path path))]
    (remove nil?
            (mapv #(let [result (parser %)]
                     (when-not (insta/failure? result) result))
                  (doall (line-seq r))))))

(defn -main [path]
  (doseq [[group-id group] (group-by #(get-in % [1 1]) (parse path))]
    (let [latest (first (sort-by #(get-in % [3 1] "0") #(* -1 (vc/version-compare %1 %2)) group))]
      (println group-id (get-in latest [3 1])))))
