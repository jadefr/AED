# AED
Esta aplicação -- desenvolvida na disciplina Algoritmos e Estruturas de Dados do Mestrado (PPGCC - UFJF) -- conta com uma estrutura de dados do tipo árvore Patricia (Patricia tree ou Radix tree) para realizar operações no banco de dados USDA (United States Department of Agriculture). 
  
  * A estrutura, criada como um IMDB (In-Memory Database), permite:
    - a definição de campos e inserção de valores (i.e., inserção de chaves primárias)
    - a instanciação de tabelas
  
  * Este tipo de estrutura foi escolhido por ser útil no tratamento de chaves de tamanho variável, podendo estas serem extremamente longas, como títulos e frases.
    
Em um segundo momento, buscando um aprimoramento da solução (e.g., operações mais rápidas e menos custosas em termo de espaço) a árvore Patricia foi transformada em uma tabela hash de árvores Patricia.
  
  * A nova estrutura permite (além da inserção e busca):
    - remoção
    - contar registros (como SELECT COUNT FROM e SELECT COUNT FROM WHERE)
    - consultas JOIN (i.e., INNER JOIN e OUTER JOIN) 
  
## Tech Stack
* Java

## Desenvolvimento
Tries, também conhecidas como árvores digitais ou árvores de prefixos, são tipos de árvores de busca utilizadas para implementar tabelas de símbolos cujas chaves são strings<sup>1</sup>. 

Em uma trie, por ser uma árvore n-ária, uma chave é recursivamente comparada a um nó da árvore até chegar ao nó procurado ou não o encontrar.

### Árvore Patrícia 
A árvore Patricia (Practical Algorithm to Retrieve Information Coded in Alphanumeric) deriva da estrutura trie, porém diferencia-se desta no aspecto de que os nós que possuem apenas um filho são agrupados -- o que reduz o gasto de memória. 

Faz-se a separação entre nós pais e nós folhas, onde os nós pais são representados por instâncias da classe Node (figura abaixo) cujos atributos *data* e *key* são nulos, enquanto que nas folhas, os atributos nulos são o *index* e o *character*.

![Image of node](https://raw.githubusercontent.com/jadefr/ontology-testing/master/node.png)

#### Busca
Baseia-se numa árvore binária, onde, partindo da raiz, se segue para o filho esquerdo, caso este satisfaça as condições de busca, ou para o direito, caso contrário.




## Footnotes
  1. https://www.ime.usp.br/pf/estruturas-de-dados/aulas/tries.html
