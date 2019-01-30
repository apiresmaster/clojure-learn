(defproject domain-modeling "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/spec.alpha "0.1.123"]
                 [cheshire "5.7.1"]
                 [failjure "1.0.1"]
                 [org.clojure/core.async "0.3.443"]
                 [bidi "2.1.2"]
                 [http-kit "2.2.0"]
                 [clj-http "3.6.1"]]
  :main ^:skip-aot hackynews.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
