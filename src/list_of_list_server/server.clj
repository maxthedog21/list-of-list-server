(ns list-of-list-server.server
  (:require
   [ring.adapter.jetty :as jetty]
   [clojure.pprint :as pprint]
   [compojure.core :as comp]
   [compojure.route :as route]
   [ring.middleware.params :refer [wrap-params]]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [list-of-list-server.MasterController :refer [app]]
   )
  )


(defonce server (atom nil))

(defn start-server []
  (reset! server
          (jetty/run-jetty (fn [req] (app req))
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
  )
