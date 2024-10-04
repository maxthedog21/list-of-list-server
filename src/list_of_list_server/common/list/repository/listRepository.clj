(ns list-of-list-server.common.list.repository.listRepository
  (:require
   [list-of-list-server.repository.db :as repo]
   )
)

(defn get-list-by-id [id]
    (let [
    sql (str "SELECT *
            FROM list
            WHERE id = " id)
        ]
      (repo/pass-sql sql)
    )
)

(defn get-all-list-children [id]
    (let [
    sql (str "SELECT *
            FROM list
            WHERE parent_id = " id)
        ]
      (repo/pass-sql sql)
    )
)

(defn add-new-list [new-list-map]
    (let [
    sql (str "INSERT INTO list(parent_id, direction)
                VALUES" (repo/item-to-values new-list-map))
          ]
      (repo/pass-sql sql)
    )
)
