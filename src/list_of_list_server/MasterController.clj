(ns list-of-list-server.MasterController
  (:require
   [ring.adapter.jetty :as jetty]
   [clojure.pprint :as pprint]
   [compojure.core :as comp]
   [compojure.route :as route]
   [ring.middleware.params :refer [wrap-params]]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [list-of-list-server.common.list.controller.ListController :refer [listRoutes]]
   )
  )


(comp/defroutes routes
listRoutes
(route/not-found {:status 404
                    :body "Not found."
                    :headers {"Content-Type" "text/plain"}})
  )

(def app (wrap-params (wrap-keyword-params (fn [req] (routes req)))))
