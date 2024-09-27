(ns list-of-list-server.core
  (:require
   [ring.adapter.jetty :as jetty]
   [clojure.pprint :as pprint]
   [compojure.core :as comp]
   [compojure.route :as route]
   )
  )

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defonce server (atom nil))
(defn resp [req]
  (cond
   (= (:uri req) "/") {:status 200 :body "<h1>Homepage</h1>
                <ul>
                    <li><a href=\"/echo\">Echo request</a></li>
                    <li><a href=\"/greeting\">Greeting</a></li>
                </ul>" :headers {"Content-Type" "text/html; charset=UTF-8"}}
   (= (:uri req) "/echo") {:status 200 :body (with-out-str (pprint/pprint req)) :headers {"Content-Type" "text/plain"}}
   (= (:uri req) "/greeting") {:status 200 :body "Hello, World" :headers {"Content-Type" "text/plain"}}
   :else {:status 240 :body "Not found." :headers {"Content-Type" "text/plain"}}
  )
  )

(defn start-server []
  (reset! server
          (jetty/run-jetty (fn [req] (resp req))
                           {:port 3001
                            :join? false
                            }
                           )
          )
  )




(defn stop-server []
  (if (not= nil @server) ;; check if there is an object in the atom
    (let [s @server]
         (.stop s)            ;; call the .stop method
         (reset! server nil)
    )
    )
  );; overwrite the atom with nil

(start-server)
