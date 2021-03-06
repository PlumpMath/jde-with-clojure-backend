(ns jdee.nrepl.nrepl
  (:require [clojure.tools.nrepl.server :as nrepl-server]
            [cider.nrepl.middleware
             classpath
             stacktrace]))


(def jdee-middleware
  "A vector of symbols for all the JDE middleware"
  '[cider.nrepl.middleware.classpath/wrap-classpath
    cider.nrepl.middleware.stacktrace/wrap-stacktrace
    ]
  )

(def jdee-nrepl-handler
  "JDE's nREPL handler"
  (apply nrepl-server/default-handler
         (map resolve jdee-middleware)))

(defn start-server []
  (let [;; todo randomize!
        port 12345
        server
        (clojure.tools.nrepl.server/start-server
         :handler jdee-nrepl-handler
         :port port)]
    ;; This message is searched for my jde-interactive.el
    ;; so don't change it without keeping the two in sync
    (println "nREPL server started on port" port)))
