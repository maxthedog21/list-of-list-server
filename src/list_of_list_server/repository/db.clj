(ns list-of-list-server.repository.db
  (:require
   [next.jdbc :as jdbc]
   )
  )


(def db {:dbtype "postgres" :dbname "postgres" :port 5432 :host "localhost" :user "postgres" :password "postgres"})

(defn pass-sql [sql]
  (let [ds (jdbc/get-datasource db)]
    (with-open [connection (jdbc/get-connection ds)]
      (jdbc/execute! connection [sql])
      )
    )
  )

(defn item-to-values [m]
  (str "("(reduce (fn [acc cur] (str acc ", " cur)) (vals m)) ")")
  )
