(defproject lein-search-result-parser "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"
            :year 2015
            :key "mit"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [instaparse "1.3.6"]
                 [version-clj "0.1.2"]]
  :main lein-search-result-parser.core
  :profiles {:uberjar {:aot :all}})
