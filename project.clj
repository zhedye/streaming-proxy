(defproject streaming-proxy "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]]

  :plugins [[lein-ancient "1.0.0-RC3"]
            ;; [cider/cider-nrepl "0.47.0"]
            ;; [refactor-nrepl "3.10.0"]
            ]

  :repl-options {:init-ns streaming-proxy.example}

  :profiles {:dev {:dependencies [[http-kit "2.8.0"]
                                  [clj-http "3.13.0"]
                                  [ring "1.13.0"]
                                  [ring/ring-mock "0.4.0"]
                                  [midje "1.10.10"]

                                  ;; [nrepl "1.2.0"]
                                  ]}})
