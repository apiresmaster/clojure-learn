(ns first-program.funcoes)
;;Não existe for e while

;;Não entendi a junção do loop e o uso da função recur, estudade de novo
(defn fact [x]
  (loop [n x prod 1] ;; parecido com o let, mas obrigatoriamente têm dois argumentos
             ;;que devem ser repasados na função de recursividade
    (if (= 1 n)  ;; this is the base case.
      prod
      (recur (dec n) (* prod n)))));;a função "recur volta para a função loop, com novos argumentos"

;;Função Recursiva - Chamada Tradicional
(defn recursividade [n]
  "Teste de função recursiva"
  (if (= 1 n)
    1  ;;retorna um como critnério de parada

   (* n (recursividade (dec n)))))

(recursividade 5);;chamada
