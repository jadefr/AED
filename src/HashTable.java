
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jade
 */
public class HashTable {

    Patricia[] tabela; // a tabela hash é um vetor de árvores Patricia
    private final static int m = 1051; // m é o tamanho da tabela

    public HashTable() {
        tabela = new Patricia[m];
    }

    public void insert(String[] data, String key) {
        int position = mod(key.hashCode(),tabela.length);
        if (tabela[position] == null) {
            Patricia tree = new Patricia();
            tabela[position] = tree;
        }
        tabela[position].insert(data, key); // insert da classe Patricia   

    }
    
    private int mod(int a, int b){ // função para dar o módulo (incluindo inteiros negativos)
        return (a % b + b)%b;
    }
    

    public String[] search(String key) {
        int position = mod(key.hashCode(),tabela.length);
        if (tabela[position] != null) {
            Node resultado = tabela[position].search(key);
            if (resultado != null) {
                return resultado.data;
            }
        }
        return null;
    }

    public Node remove(String key) {
        int position = mod(key.hashCode(),tabela.length);
        if (tabela[position] != null) {
            return tabela[position].remove(key);
        }
        return null;
    }
    
    public List<Node> inOrder(){
        List<Node> folhas = new ArrayList<>(); // guarda os registros de todas as árvores
        for(int i = 0; i < tabela.length; i++){
            if(tabela[i] != null){
                folhas.addAll(tabela[i].inOrder());
            }
        }
        return folhas;
    }
    
}
