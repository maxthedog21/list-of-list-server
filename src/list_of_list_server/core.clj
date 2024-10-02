(ns list-of-list-server.core
  (:require
   [list-of-list-server.server :refer [start-server stop-server]]
   )
  )

(start-server)
(stop-server)



(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
