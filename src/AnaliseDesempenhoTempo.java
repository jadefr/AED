
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeString.concat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jade
 */
public class AnaliseDesempenhoTempo {

    public static void insertAll(int times) throws IOException {

        long minTime = Integer.MAX_VALUE;
        long maxTime = Integer.MIN_VALUE;
        long sumTime = 0;
        for (int i = 0; i < times; i++) {
            Database database = new Database();
            long tempoInicial = System.currentTimeMillis();
            database.importFile("usda.sql");
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
        System.out.println("Min insertion time: " + minTime + " nanoseconds");
        System.out.println("Max insertion time: " + maxTime + " nanoseconds");
        System.out.println("Average insertion time: " + sumTime / times + " nanoseconds");

    }

    public static void searchAll() throws IOException {
        Database database = new Database();
        database.importFile("usda.sql");
        long sumTime = 0;
        int cont = 0;
        for (EntryData dado : database.importer.registro) {
            if (dado.chave != null) {
                long tempoInicial = System.nanoTime();
                String[] busca = database.search(dado.tableName, dado.chave);
                long tempoFinal = System.nanoTime();
                long tempoGasto = tempoFinal - tempoInicial;

                if (busca != null) {
                    cont++;
                    if (cont % 2000 == 0) {
                        sumTime += tempoGasto;
                        System.out.println(sumTime + "\t" + cont);
                    }
                }
            }

        }
    }

    public void insertOneTable(int times) throws IOException { // times é a quantidade de testes
// O método calcula os tempos mínimo, médio e máximo de se inserir todos os registros em uma tabela
        long minTime = Integer.MAX_VALUE;
        long maxTime = Integer.MIN_VALUE;
        long sumTime = 0;
        for (int i = 0; i < times; i++) {
            Database database = new Database();
            long tempoInicial = System.currentTimeMillis();
            database.importOneTable("usda.sql", "fd_group");
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
        System.out.println("Average insertion time: " + sumTime / times + " nanoseconds");

    }

    public void insertOneTable(String tableName) throws IOException { //calculo o tempo de inserção acumulado por quantidade de registros
        Database database = new Database();
        database.importOneTable("usda_original.sql", tableName);
        long sumTime = 0;
        int cont = 0;
        for (EntryData dado : database.importer.registro) {
            if (dado.tableName.equals(tableName)) {
                if (dado.chave != null) {
                    long tempoInicial = System.nanoTime();
                    String[] busca = database.search(dado.tableName, dado.chave);
                    long tempoFinal = System.nanoTime();
                    long tempoGasto = tempoFinal - tempoInicial;

                    if (busca != null) {
                        cont++;
                        if (cont % 200 == 0) { // pega um registro a cada 200
                            sumTime += tempoGasto;
                            System.out.println(sumTime + "\t" + cont);
                        }
                    }
                }
            }

        }
    }

    public void searchAllFromOneTable(String tableName) throws IOException { //Calcula o tempo de busca acumulado por quantidade de registros
        Database database = new Database();
        database.importOneTable("usda_original.sql", tableName);
        long sumTime = 0;
        int cont = 0;
        for (EntryData dado : database.importer.registro) {
            if (dado.tableName.equals(tableName)) {
                if (dado.chave != null) {
                    long tempoInicial = System.nanoTime();
                    String[] busca = database.search(dado.tableName, dado.chave);
                    long tempoFinal = System.nanoTime();
                    long tempoGasto = tempoFinal - tempoInicial;

                    if (busca != null) {
                        cont++;
                        if (cont % 500 == 0) {
                            sumTime += tempoGasto;
                            System.out.println(sumTime + "\t" + cont);
                        }
                    }
                }

            }
        }
    }

   public void insertOneTableTime(int times, String tableName) throws IOException { // times é a quantidade de testes
// O método calcula os tempos mínimo, médio e máximo para se inserir todos os registros de uma tabela
        long minTime = Integer.MAX_VALUE;
        long maxTime = Integer.MIN_VALUE;
        long sumTime = 0;
        for (int i = 0; i < times; i++) {
            Database database = new Database();
            long tempoInicial = System.currentTimeMillis();
            database.importOneTable("usda_original.sql", tableName);
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
   
    public void removeAllFromOneTable(String tableName) throws IOException { //Calcula o tempo de remoção acumulado por quantidade de registros
        Database database = new Database();
        database.importOneTable("usda_original.sql", tableName);
        long sumTime = 0;
        int cont = 0;
        for (EntryData dado : database.importer.registro) {
            if (dado.tableName.equals(tableName)) {
                if (dado.chave != null) {
                    long tempoInicial = System.nanoTime();
                    database.remove(dado.tableName, dado.chave);
                    cont++;
                    long tempoFinal = System.nanoTime();
                    long tempoGasto = tempoFinal - tempoInicial;

                    if (cont % 500 == 0) {
                        sumTime += tempoGasto;
                        System.out.println(sumTime + "\t" + cont);
                    }

                }

            }
        }
    }
    
    
    
}



