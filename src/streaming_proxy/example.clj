(ns streaming-proxy.example
  [:require
   [clojure.java.io :as io]
   [streaming-proxy.core :as sp]
   [org.httpkit.server :as hks]
   [clj-http.client :as client]
   [ring.util.io]])

(defn proxy-response-handler
  [res]
  (if (>= (:status res) 400)
    (merge res {:body (str "downstream error: " (slurp (io/reader (:body res))))})
    res))

(defn proxy-wrapper
  [handler endpoint-port]
  #(handler (merge % {:streaming-proxy-url (str "http://localhost:" endpoint-port (:uri %))
                      :streaming-proxy-response-handler proxy-response-handler
                      :timeout 1000})))

(defn proxy-app
  [endpoint-port]
  (proxy-wrapper sp/proxy-handler endpoint-port))

(def app (proxy-app 9001))

(defonce server-atom (atom nil))
(defn start-server []

  (if @server-atom
    (do (.stop @server-atom)
        (.start @server-atom))
    (reset! server-atom (org.httpkit.server/run-server app {:port 9000}))))
