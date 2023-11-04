(ns foo
  (:require
   [clj-http.client :as client]))

(def home (client/get "http://www.clojure.org"))

(sort-by second > (frequencies (:body home)))
