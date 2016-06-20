(ns i-before-e.core
  (:require [clojure.string :as s]))

(defn plausible?
  "Word is plausible because it has either i after e not before c or i before e after c"
  [word]
  (let [lc-word (s/lower-case word)]
    (or
     (not (re-seq #"(ei)|(ie)" lc-word))
     (re-seq #"(?<!c)ie" lc-word)
     (re-seq #"cei" lc-word))))

(defn count-plausible-not-plausible
  "Increment either the plausible or not-plausible count in map depending on result of plausible? for given word."
  [{:keys [plausible not-plausible]} word]
  (if (plausible? word)
    {:plausible (inc plausible) :not-plausible not-plausible}
    {:plausible plausible :not-plausible (inc not-plausible)}))

(defn plausible-vs-not-plausible
  "Reduce over each word read from wordlist determining if it's plausible or not plausible and keep a map of counts of each."
  [words]
  (reduce count-plausible-not-plausible {:plausible 0 :not-plausible 0} words))

(defn- read-word-unixdist-list []
  (s/split (slurp "http://www.puzzlers.org/pub/wordlists/unixdict.txt") #"\s"))

(defn i-before-e-except-after-c-plausible?
  "Rule 'I before e except after c is plausible if the plausible count is more than 2 times the not plausible count."
  [word-list]
  (let [{:keys [plausible not-plausible]} (plausible-vs-not-plausible word-list)]
    (< (* 2 not-plausible) plausible)))

(i-before-e-except-after-c-plausible? (read-word-unixdist-list))
