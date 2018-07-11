
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Database {

    List<Table> tables;
    ImportData importer;

    public Database() {
        this.tables = new LinkedList<>();
    }

    public Table getTableByName(String name) {
        for (Table table : tables) {
            if (table.name.equals(name)) {
                return table;
            }
        }
        return null;
    }

    public String insert(String tableName, String[] data, String[] campos) {
        Table table = getTableByName(tableName);
        if (table != null) {
//            System.out.println(tableName);
//            System.out.println(Arrays.toString(data));
//            System.out.println(Arrays.toString(campos));
            return table.insert(data, campos);
        }
        return null;
    }

    public String[] search(String tableName, String key) {
        Table table = getTableByName(tableName);
        if (table != null) {
            String[] result = table.search(key);
            printTable(table.header, result);
            return result;
        }
        return null;
    }

    public void createTable(String name, String[] header) {
        tables.add(new Table(name, header));
    }

    public void printTable(String[] headers, String[] results) {
        if (headers != null && results != null) {
            for (String header : headers) {
                System.out.print(header + "\t");
            }
            System.out.println("");

            for (String result : results) {
                System.out.print(result + "\t");
            }

        }
    }

    public void setPrimaryKeys(String[] primaryKeys, String tableName) {
        Table table = getTableByName(tableName);
        if (table != null) {
            table.setPrimaryKeys(primaryKeys);
        }
    }

    public void importFile(String fileName) throws IOException {
        importer = new ImportData(fileName);

        List<EntryCreateTable> tabelas = importer.tabela;
        for (EntryCreateTable t : tabelas) {
            createTable(t.tableName, t.headers);
        }

        List<EntryKey> chaves = importer.chave;
        for (EntryKey k : chaves) {
            setPrimaryKeys(k.headers, k.tableName);
        }

        List<EntryData> dados = importer.registro;
        for (EntryData d : dados) {
            d.chave = insert(d.tableName, d.data, d.headers);
        }

    }

    public void innerJoin(String tableName1, String tableName2) {
        // considera-se que campo1 e campo2 são chaves primárias 
        Table table1 = getTableByName(tableName1);
        Table table2 = getTableByName(tableName2);
        List<Node> lista1 = table1.getAllRecords();
        List<String[]> registros = new ArrayList<>();
        for (Node node : lista1) {
            String[] dado2 = table2.search(node.getKey());
            String[] dado1 = node.getData();
            if ((dado2 != null) && (dado1 != null)) {
                String[] dados = concat(dado1, dado2);
                registros.add(dados);
            }
        }
        String[] cabecalho = concatHeader(table1, table2);
        printJoin(cabecalho, registros);
    }

    public void leftJoin(String tableName1, String tableName2) {
        Table table1 = getTableByName(tableName1);
        Table table2 = getTableByName(tableName2);
        List<Node> lista1 = table1.getAllRecords();
        List<String[]> registros = new ArrayList<>();
        for (Node node : lista1) {
            String[] dado2 = table2.search(node.getKey());
            String[] dado1 = node.getData();
            if ((dado1 != null) && (dado2 != null)) {
                String[] dados = concat(dado1, dado2);
                registros.add(dados);
            }
            if ((dado1 != null) && (dado2 == null)) {
                String[] registro2 = ifNull(table2);
                String[] dados = concat(dado1, registro2);
                registros.add(dados);
            }
        }
        String[] cabecalho = concatHeader(table1, table2);
        printJoin(cabecalho, registros);
    }

    public void rightJoin(String tableName1, String tableName2) {
        Table table1 = getTableByName(tableName1);
        Table table2 = getTableByName(tableName2);
        List<Node> lista1 = table1.getAllRecords();
        List<String[]> registros = new ArrayList<>();
        for (Node node : lista1) {
            String[] dado2 = table2.search(node.getKey());
            String[] dado1 = node.getData();
            if ((dado1 != null) && (dado2 != null)) {
                String[] dados = concat(dado1, dado2);
                registros.add(dados);
            }
            if ((dado1 == null) && (dado2 != null)) {
                String[] registro1 = ifNull(table1);
                String[] dados = concat(registro1, dado2);
                registros.add(dados);
            }
        }
        String[] cabecalho = concatHeader(table1, table2);
        printJoin(cabecalho, registros);
    }

    public void fullJoin(String tableName1, String tableName2) {
        Table table1 = getTableByName(tableName1);
        Table table2 = getTableByName(tableName2);
        List<Node> lista1 = table1.getAllRecords();
        List<String[]> registros = new ArrayList<>();
        for (Node node : lista1) {
            String[] dado2 = table2.search(node.getKey());
            String[] dado1 = node.getData();
            if ((dado1 != null) && (dado2 != null)) {
                String[] dados = concat(dado1, dado2);
                registros.add(dados);
            }
            if ((dado1 == null) && (dado2 != null)) {
                String[] registro1 = ifNull(table1);
                String[] dados = concat(registro1, dado2);
                registros.add(dados);
            }
            if ((dado1 != null) && (dado2 == null)) {
                String[] registro2 = ifNull(table2);
                String[] dados = concat(dado1, registro2);
                registros.add(dados);
            }
        }
        String[] cabecalho = concatHeader(table1, table2);
        printJoin(cabecalho, registros);
    }

    public String[] ifNull(Table table) {
        String[] header = table.getHeader();
        String[] registro = new String[header.length];
        for (int i = 0; i < registro.length; i++) {
            registro[i] = " ";
        }
        return registro;
    }

    public String[] concatHeader(Table table1, Table table2) {
        String[] header1 = table1.getHeader();
        String[] header2 = table2.getHeader();
        String[] cabecalho = concat(header1, header2);
        return cabecalho;
    }

    public void printJoin(String[] cabecalho, List<String[]> registros) {
        for (int i = 0; i < cabecalho.length; i++) {
            System.out.print(cabecalho[i] + "\t");
        }
        System.out.println("");
        for (String[] registro : registros) {
            if (registro != null) {
                for (int i = 0; i < registro.length; i++) {
                    System.out.print(registro[i] + "\t");
                }
                System.out.println("");
            }
        }
    }

    public String[] concat(String[] dado1, String[] dado2) {
        String[] dados = new String[dado1.length + dado2.length];
        for (int i = 0; i < dado1.length; i++) {
            dados[i] = dado1[i];
        }
        for (int i = dado1.length, j = 0; i < dados.length; i++, j++) {
            dados[i] = dado2[j];
        }
        return dados;
    }

    public void remove(String tableName, String key) {
        Table table = getTableByName(tableName);
        table.remove(key);
    }

    public void countAll(String tableName) {
        Table table = getTableByName(tableName);
        List<Node> lista = table.getAllRecords();
        int count = lista.size();
        System.out.println(count);
    }

    public int getFieldPosition(Table table, String campo) {
        String[] header = table.getHeader();
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(campo)) {
                return i;
            }
        }
        return -1;
    }

    public void countWhere(String tableName, String campo, String value) {
        Table table = getTableByName(tableName);
        List<Node> lista = table.getAllRecords();
        int position = getFieldPosition(table, campo);
        int count = 0;
        for (Node node : lista) {
            String[] data = node.getData();
            if (data[position].equals(value)) {
                count++;
            }
        }
       // System.out.println(count);
    }

    //public void printNodes(String tableName){
    //    Table table = getTableByName(tableName);
    //    table.getAllRecords();
    //}
    public void importOneTable(String fileName, String nomeTabela) throws IOException { //método para criar apenas uma tabela
        importer = new ImportData(fileName);
        List<EntryCreateTable> tabelas = importer.tabela;
        for (EntryCreateTable t : tabelas) {
            if (t.tableName.equals(nomeTabela)) {
                createTable(t.tableName, t.headers);
            }
        }
        List<EntryKey> chaves = importer.chave;
        for (EntryKey k : chaves) {
            if (k.tableName.equals(nomeTabela)) {
                setPrimaryKeys(k.headers, k.tableName);
            }
        }
        List<EntryData> dados = importer.registro;
        for (EntryData d : dados) {
            if (d.tableName.equals(nomeTabela)) {
                d.chave = insert(d.tableName, d.data, d.headers);
            }
        }

    }

    // Análise de Desempenho no tempo
      public void countAllTime(int times, String tableName) throws IOException {

        long minTime = Integer.MAX_VALUE;
        long maxTime = Integer.MIN_VALUE;
        long sumTime = 0;
        for (int i = 0; i < times; i++) {
            //Database database = new Database();
            //database.importOneTable("usda_original.sql",tableName);
            long tempoInicial = System.currentTimeMillis();
            countAll(tableName);
            long tempoFinal = System.currentTimeMillis();
            long tempoGasto = tempoFinal - tempoInicial;

            if (tempoGasto < minTime) {
                minTime = tempoGasto;
            }
            if (tempoGasto > maxTime) {
                maxTime = tempoGasto;
            }
            sumTime += tempoGasto;

        }
        System.out.println("Min insertion time: " + minTime + " milliseconds");
        System.out.println("Max insertion time: " + maxTime + " milliseconds");
        System.out.println("Average insertion time: " + sumTime / times + " milliseconds");

    }
     
     public void countWhereTime(int times,String tableName, String campo, String value) throws IOException {

        long minTime = Integer.MAX_VALUE;
        long maxTime = Integer.MIN_VALUE;
        long sumTime = 0;
        for (int i = 0; i < times; i++) {
            //Database database = new Database();
            //database.importOneTable("usda_original.sql",tableName);
            long tempoInicial = System.currentTimeMillis();
            countWhere(tableName,campo,value);
            long tempoFinal = System.currentTimeMillis();
            long tempoGasto = tempoFinal - tempoInicial;

            if (tempoGasto < minTime) {
                minTime = tempoGasto;
            }
            if (tempoGasto > maxTime) {
                maxTime = tempoGasto;
            }
            sumTime += tempoGasto;

        }
        System.out.println("Min insertion time: " + minTime + " milliseconds");
        System.out.println("Max insertion time: " + maxTime + " milliseconds");
        System.out.println("Average insertion time: " + sumTime / times + " milliseconds");

    }
     
       public void joinTime(int times,String tableName1, String tableName2) throws IOException {

        long minTime = Integer.MAX_VALUE;
        long maxTime = Integer.MIN_VALUE;
        long sumTime = 0;
        for (int i = 0; i < times; i++) {
            //Database database = new Database();
            //database.importFile("usda.sql");
            long tempoInicial = System.currentTimeMillis();
            fullJoin(tableName1,tableName2);
            long tempoFinal = System.currentTimeMillis();
            long tempoGasto = tempoFinal - tempoInicial;

            if (tempoGasto < minTime) {
                minTime = tempoGasto;
            }
            if (tempoGasto > maxTime) {
                maxTime = tempoGasto;
            }
            sumTime += tempoGasto;

        }
        System.out.println("Min insertion time: " + minTime + " milliseconds");
        System.out.println("Max insertion time: " + maxTime + " milliseconds");
        System.out.println("Average insertion time: " + sumTime / times + " milliseconds");

    }
     
    
}
