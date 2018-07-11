
import java.util.List;

public class Table {

    String name;
    //Patricia arvore;
    HashTable arvore;
    String[] header;
    String[] primaryKeys;

    public Table(String name, String[] header) {
        this.name = name;
        this.header = header;
        arvore = new HashTable();
        //HashTable hashTable = new HashTable(617);
    }

    public void setPrimaryKeys(String[] primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public String insert(String[] data, String[] campos) {
        if (primaryKeys != null) { // se primary keys = nulo, a tabela não tem chave primaria
            Integer[] keyPositions = new Integer[primaryKeys.length];
            int cont = 0;
            for (int j = 0; j < primaryKeys.length; j++) {
                for (int i = 0; i < header.length; i++) {
                    if (primaryKeys[j].equals(header[i])) {
                        keyPositions[cont++] = i;
                    }
                }
            }
            String keyString = "";
            for (int i = 0; i < keyPositions.length; i++) {
                keyString = keyString.concat(data[keyPositions[i]]);
            }
            String[] temporario = new String[header.length];
            if (data.length < campos.length) {

                for (int i = 0; i < campos.length; i++) { // corrige problemas onde os ultimos dados não foram capturados por serem tabs ("\t")
                    if (i < data.length) {
                        temporario[i] = data[i];
                    } else {
                        temporario[i] = "";
                    }
                }
                data = temporario;
            }
            temporario = new String[header.length];
            for (int i = 0; i < header.length; i++) { // preenche os campos que não foram informados, adicionando uma string vazia
                for (int j = 0; j < campos.length; j++) {
                    if (header[i].equals(campos[j])) {
                        temporario[i] = data[j];
                        break;
                    } else {
                        temporario[i] = "";
                    }
                }
            }
            arvore.insert(temporario, keyString);
            return keyString;
        }
        return null;
    }

    public String[] search(String key) {
        return arvore.search(key);
    }

    public List<Node> getAllRecords() {
       return arvore.inOrder();
    }

    public String[] getHeader() {
        return header;
    }

    public Node remove(String key) {
        return arvore.remove(key);
    }

}
