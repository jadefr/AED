# AED
Esta aplicação -- desenvolvida na disciplina Algoritmos e Estruturas de Dados do Mestrado (PPGCC - UFJF) -- conta com uma estrutura de dados do tipo árvore Patricia (Patricia tree ou Radix tree) para realizar operações no banco de dados USDA (United States Department of Agriculture). 
  
  * A estrutura, criada como um IMDB (In-Memory Database), permite:
    - a definição de campos e inserção de valores (i.e., inserção de chaves primárias)
    - busca
    - remoção
    - a instanciação de tabelas
  
  * Este tipo de estrutura foi escolhido por ser útil no tratamento de chaves de tamanho variável, podendo estas serem extremamente longas, como títulos e frases.
    
Em um segundo momento, buscando um aprimoramento da solução (e.g., operações mais rápidas e menos custosas em termo de espaço) a árvore Patricia foi transformada em uma tabela hash de árvores Patricia.
  
  * A nova estrutura permite (além da inserção, busca e remoção):
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

| ![Image of node](https://raw.githubusercontent.com/jadefr/ontology-testing/master/node.png) | 
|:--:| 
| *Representação da classe Node* |

#### Inserção
É feita comparando-se alfanumericamente as Strings correspondentes às chaves primárias, que são armazenadas sempre como nós folhas. 

O processo de inserção começa pela raiz. Caso esta seja um registro:

    1. Compara-se a chave correspondente a este registro com a chave do registro que se queira inserir.
 
    2. Cria-se um novo nó com a primeira posição na qual as Strings divergem e com o caractere presente nesta posição no nó raiz. Este novo nó passa a ser a raiz da árvore
  
    3. Dentre os dois registros (o que se quer inserir e a raiz), o que possuir o caractere especificado pelo nó raiz, na posição especificada por este, passa a ser o filho esquerdo, enquanto que o outro passa a ser o filho direito.
  
    4. Repete-se recursivamente, resultando em uma árvore binária onde todos os nós têm dois filhos.

##### Complexidade
Pela literatura, sabe-se que o número médio de comparações a serem feitas nessa árvore é *log(N)*, onde *N* é o número de registros. 

Porém como os registros se apresentam (no arquivo de dump) ordenados alfabeticamente, espera-se que as operações realizadas na árvore tendam ao pior caso, que seria da ordem *N* (como se pode ver expresso no gráfico abaixo).

| ![Image of insercao](https://raw.githubusercontent.com/jadefr/ontology-testing/master/insercao.png) | 
|:--:| 
| *Inserção de todos os registros na tabela nut_data* |

#### Busca
Baseia-se na comparação da chave que se quer buscar com a raiz da árvore.

  **1.** Considerando que a árvore possua mais que um registro, e, portanto, a raiz não é um registro, compara-se o caractere presente na raiz com com o caractere presente na chave na posição indicada pela raiz.
  
  **2.** Caso a comparação seja positiva, segue-se para a esquerda e repete-se o procedimento.
  
  **3.** Caso contrário, segue-se para a direita recursivamente.

  **4.** A recursão pára ao se atingir o nó folha correspondete à chave procurada.
  
  **5.** Caso não a ache, o retorno é nulo.


## Referências
  1. https://www.ime.usp.br/pf/estruturas-de-dados/aulas/tries.html
