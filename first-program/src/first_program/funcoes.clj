(ns first-program.funcoes)
;;Exemplificar as duas formas de definir fuções
;;com defn e com def + fn

(defn calculaImposto
  "Calcula imposto"
  [tx]
  ((if true
    (let [varlocal 4]
      (+ 4 tx)))))

(calculaImposto 1)

(defn calculaImposto
  "Calcula imposto"
  [tx]
  ((if true
    (let [varlocal 4]
      (+ 4 tx)))))

(defn foo
  "I don't do a whole lot."
  [x
   (println x "Hello, World!")])

(defn par
  "Verifica se um número é par."
  [x]
  (= (rem x 2) 0))

(defn pares
  "Verifica números pares na lista."
  [lista]
  (filter par lista))

(pares [2 4 5 6 7 8 9 10 11 12 13 14])
