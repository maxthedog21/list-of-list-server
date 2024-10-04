(ns list-of-list-server.common.list.controller.ListController
  (:require
   [ring.adapter.jetty :as jetty]
   [clojure.pprint :as pprint]
   [compojure.core :as comp]
   [compojure.route :as route]
   [ring.middleware.params :refer [wrap-params]]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   )
  )


(def root "List")


(def listRoutes (comp/routes
  (comp/GET "/" []
    {
     :status 200
     :body  "<h1>My Site</h1>
                           <ul>
                               <li><a href=\"/echo\">Echo request</a></li>
                               <li><a href=\"/greeting\">Greeting</a></li>
                           </ul>"
     :headers {"Content-Type" "text/html; charset=UTF-8"}
     }
    )
  (comp/ANY "/echo" req {:status 200
                         :body (with-out-str (pprint/pprint req))  ;; use the req binding
                         :headers {"Content-Type" "text/plain"}})
  (comp/GET "/greeting" [] {:status 200
                            :body "Hello, Maximilian!"
                            :headers {"Content-Type" "text/plain"}})
  )
  )










;; overwrite the atom with nil
