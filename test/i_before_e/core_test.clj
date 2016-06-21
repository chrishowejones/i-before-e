(ns i-before-e.core-test
  (:require [clojure.test :refer :all]
            [i-before-e.core :refer :all]))

(deftest test-update-counts
  (testing "Given {:ie 1 :cie 0 :ei 0 :cei 0} and 'fierce' return {:ie 2 :cie 0 :ei 0 :cei 0}"
    (is (= {:ie 2 :cie 0 :ei 0 :cei 0} (update-counts {:ie 1 :cie 0 :ei 0 :cei 0} ["fierce" 1]))))
  (testing "Given {:ie 0 :cie 1 :ei 0 :cei 0} and 'ancient' return {:ie 0 :cie 2 :ei 0 :cei 0}"
    (is (= {:ie 0 :cie 2 :ei 0 :cei 0} (update-counts {:ie 0 :cie 1 :ei 0 :cei 0} ["ancient" 1]))))
  (testing "Given {:ie 0 :cie 0 :ei 1 :cei 0} and 'counterfeit' return {:ie 0 :cie 0 :ei 2 :cei 0}"
    (is (= {:ie 0 :cie 0 :ei 2 :cei 0} (update-counts {:ie 0 :cie 0 :ei 1 :cei 0} ["counterfeit" 1]))))
  (testing "Given {:ie 0 :cie 0 :ei 0 :cei 1} and 'receive' return {:ie 0 :cie 0 :ei 0 :cei 2}"
    (is (= {:ie 0 :cie 0 :ei 0 :cei 2} (update-counts {:ie 0 :cie 0 :ei 0 :cei 1} ["receive" 1]))))
  (testing "Given {:ie 0 :cie 0 :ei 0 :cei 0} and 'dummy' return {:ie 0 :cie 0 :ei 0 :cei 0}"
    (is (= {:ie 0 :cie 0 :ei 0 :cei 0} (update-counts {:ie 0 :cie 0 :ei 0 :cei 0} ["dummy" 1])))))

(deftest test-count-ie-ei-combinations
  (testing "Given [\"fierce\", \"counterfeit\", \"ancient\", \"the\", \"conceit\", \"dummy\"] expect {:ie 1, cie 1, :ei 1 :cei 1}"
    (is (= {:ie 1 :cie 1 :ei 1 :cei 1} (count-ie-ei-combinations (apply-freq-1 ["fierce", "counterfeit", "ancient", "the", "conceit", "dummy"])))))
  (testing "Given [\"and\", \"dummy\", \"ancient\", \"the\", \"fred\"] expect {:ie 1, cie 1, :ei 1 :cei 1}"
    (is (= {:ie 0 :cie 0 :ei 0 :cei 0} (count-ie-ei-combinations (apply-freq-1 ["and", "dummy", "the", "fred"]))))))

(deftest test-i-before-e-except-after-c-plausible?
  (testing "Given list of words 'fierce', 'die', 'their', 'the', 'and' expect true"
    (is (i-before-e-except-after-c-plausible? "Check test" (apply-freq-1 ["fierce" "die" "the" "and"]))))
  (testing "Given list of words 'fierce', 'die', 'their' expect false"
    (is (not (i-before-e-except-after-c-plausible? "Check test" (apply-freq-1 ["fierce" "ancient" "coefficient" "their"]))))))
