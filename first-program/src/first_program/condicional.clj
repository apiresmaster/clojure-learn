(ns first-program.core)

;;IF
;;Igual JS, basta testar se é verdade para entrar no IF.
(if true
  (println "Estou dentro do if")

 (println "Não entrou no if"))

;;IF - Do
;;Executa um bloco de código quando a condição for verdadeira
(if true
  ;;Bloco a ser executado
  (do
     (println "Bloco 1 dentro do If")
     (+ 1 2 3 4)
     (println "Bloco 3 dentro do If"))
  ;;Fim do bloco
 (println "Bloco fora do if"))

;;IF Ternário
(if true "If verdadeiro" "If falso")
;;Imprimindo a saida acima
(println (if true "If verdadeiro" "If falso"))
;;Negação do IF
;;Assim evita aninhar várias funções como o not para negar o if
(println (if-not true "Verdade" "Falso"))
;;When é um IF sem else que executa um bloco, sintaxe mais limpa que usar IF + Do
(when (= (+ 2 2) 4)
  (println "Maths works!")
  (println "Hooray!"))
