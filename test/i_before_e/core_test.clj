(ns i-before-e.core-test
  (:require [clojure.test :refer :all]
            [i-before-e.core :refer :all]))

(deftest test-ei-not-after-c
  (testing "Given 'albeit'  return not plausible."
    (is (not (plausible? "albeit"))))
  (testing "Given 'their'  return not plausible."
    (is (not (plausible? "their")))))

(deftest test-no-ie-or-ie
  (testing "Given 'the' return plausible."
    (is (plausible? "the")))
  (testing "Given 'line' return plausible."
    (is (plausible? "line")))
  (testing "Given 'efghi' return plausible."
    (is (plausible? "efghi"))))

(deftest test-ie-after-c-not-plausible
  (testing "Given 'recieve' return not plausible."
    (is (not (plausible? "recieve")))))

(deftest test-ei-after-c
  (testing "Given 'receive' return plausible."
    (is (plausible? "receive"))))

(deftest test-ie-not-after-c
  (testing "Given 'fierce' return plausible"
    (is (plausible? "fierce"))
    (is (plausible? "Fierce"))
    (is (plausible? "FIERCE"))))

(deftest test-count-plausible-not-plausible
  (testing "Given {:plausible 1 :not-plausible 0} and 'fierce' return {:plausible 2 :not-plausible 0}"
    (is (= {:plausible 2 :not-plausible 0} (count-plausible-not-plausible {:plausible 1 :not-plausible 0} "fierce"))))
  (testing "Given {:plausible 1 :not-plausible 1} and 'their' return {:plausible 1 :not-plausible 2}"
    (is (= {:plausible 1 :not-plausible 2} (count-plausible-not-plausible {:plausible 1 :not-plausible 1} "their")))))

(deftest test-plausible-vs-not-plausible
  (testing "Given 'fierce', 'the', 'albeit', 'die', 'their' should get 3 plausible and 2 not plausible"
    (is (= {:plausible 3 :not-plausible 2} (plausible-vs-not-plausible ["fierce" "the" "albeit" "DIE" "ThEiR"])))))


(deftest test-i-before-e-except-after-c-plausible?
  (testing "Given list of words 'fierce', 'die', 'their', 'the', 'and' expect true"
    (is (i-before-e-except-after-c-plausible? ["fierce" "die" "their" "the" "and"])))
  (testing "Given list of words 'fierce', 'die', 'their' expect false"
    (is (not (i-before-e-except-after-c-plausible? ["fierce" "die" "their"]))))
)
