(ns jdee.nrepl.version)

(def version
  "Current version of JDEE nREPL, map of :major, :minor, :incremental, and :qualifier."
  (let [version-string "0.1-snapshot"]
    (assoc (->> version-string
                (re-find #"(\d+)\.(\d+)\.(\d+)-?(.*)")
                rest
                (zipmap [:major :minor :incremental :qualifier]))
           :version-string version-string)))
