(set-env!
  :dependencies  '[[adzerk/bootlaces    "0.1.12" :scope "test"]
                   [org.clojure/clojure "1.7.0"]]
  :source-paths   #{"src"})

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.1.0")

(bootlaces! +version+)

(task-options!
  pom {:project 'degree9/lounge.app
       :version +version+
       :description "ClojureScript components for lounge-clj"
       :url         "https://github.com/degree9/lounge.app"
       :scm         {:url "https://github.com/degree9/lounge.app"}})
