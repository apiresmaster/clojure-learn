(ns first-program.core)

;;Definindo variável global
(def nomeVariavel "Alexandre")
;;Definindo variável local
;;exemplo do if ela tem um bloco local, assim temos que usar ela apenas neste bloco, chamando funções etc
;;Exemplo 1
(let [var1 "Var local"]
  (println var1))
;;Exemplo 2

;;IPC - Não existe conceito de classe, a estrutura é baseada no Namespace
;que guarda um conjunto de funções comuns
;DEFININDO COLEÇÕES
[0 1 2 3 4];Array
#{0 1 2 3 4};SET - Não permite item repetido
{:item1 "Item 1" :item2 "Item 2" :item3 "Item 3"} ;MAP - Conjunto com chave e valor
'(1 2 3 4) ;LIST
;;ADICIONANDO ITEM NA COLEÇÃO
;IPC - Uma nova coleção é criada, devido a imutabilidade;
(conj [1 2] 3)
(conj #{1 2 3} 3);Não adiciona pois já existe o item
(conj #{1 2 3} 4)
(conj {:it1 "Item 1" :it2 "Item 2"} :it3 "Item 3")
(assoc {:i1 01 :i2 02} :i3 03);Exclusivo p/ Map
;;RECUPERAR VALOR DE COLEÇÃO
(get [1, 2, 3, 4, 5] 3)
#{1, 2, 3, 4, 5, 0}
(:bar {:foo "Pires" :bar "Jovi" :quux1 "Nascimento"})
(get-in {:foo [{:quux 42} {:quuxx 43}] :bar [{:quux1 44} {:quux2 45}]} [:bar 0])
//BINDING de estrutura
(let [[a b c] ["Pires" "Nascimento"]] b)
(let [[a & outros] ["Pires" "Nascimento" "Joviana" "Todos"]] outros) ;Os demais itens ficam em outra coleção chamada de "outros"
(let [{foo :foo it2 :it2}{:foo "Item 1" :it2 "Item 2" :it3 "Item 3"}] it2)
;;Forma mais simples de escrever a linha acima, omitimos os itens mapeados
(let [{:keys [foo it2 it3]} {:foo "Item 1" :it2 "Item 2" :it3 "Item 3"}])

;;FUNÇÕES
;definir constante
(def BASE_URL "https://apiresmaster.github.com")
;Funções privada
(defn ^:private fnTeste
  [];mesmo que não tenha parametro temos q passar o array vazio aqui
  (str "Teste"))
(fnTeste)
;Sobrecarga de funções
(defn pegaNome
  "Exibe o nome da pessoa, caso não informe o nome exibe uma msg"
  ([]
   pegaNome "Nome Não Informado")
  ([nome]
   (str "Meu nome é " nome " hehehe!")))
(pegaNome)

;;IPC - para chamar funções do Java usar o "ponto" e a função depois o tipo
;abaixo usamos a função length, o texto é convertido implicitamente para String
(.length " Alexandre de Souza Pires ")
