# AED
Esta aplicação -- desenvolvida na disciplina Algoritmos e Estruturas de Dados do Mestrado (PPGCC - UFJF) -- conta com uma estrutura de dados do tipo árvore Patricia (Patricia tree ou Radix tree) para realizar operações no banco de dados USDA (United States Department of Agriculture). 
  
  * A estrutura, criada como um IMDB (In-Memory Database), permite:
    - a definição de campos e inserção de valores (i.e., inserção de chaves primárias)
    - a instanciação de tabelas
    
  * Em um segundo momento, buscando um aprimoramento da solução (e.g., operações mais rápidas e menos custosas em termo de espaço) a árvore Patricia foi transformada em uma tabela hash de árvores Patricia.
  
  * A nova estrutura permite (além da inserção e busca):
    - remoção
    - contar registros (como SELECT COUNT FROM e SELECT COUNT FROM WHERE)
    - consultas JOIN (i.e., INNER JOIN e OUTER JOIN) 
  
## Tech Stack
* Java

## O que aprendi

* Java
* Desenvolvimento de estruturas do tipo árvore e tabela hash
* Operações CRUD e join
