
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {
        
// Os nomes de arquivo, tabela e campo deve ser digitados corretamente.

        boolean sair = false;
        System.out.println("Digite o nome do arquivo a ser indexado.");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        Database database = new Database();
        System.out.println("Indexando "+fileName);
        database.importFile(fileName);
        while (sair != true) {
            System.out.println("Digite a opção desejada:");
            System.out.println("1. Busca");
            System.out.println("2. Remoção");
            System.out.println("3. Contar todos os registros de uma tabela");
            System.out.println("4. Contar registros de uma tabela que possuem um mesmo campo igual");
            System.out.println("5. Inner Join");
            System.out.println("6. Left Outer Join");
            System.out.println("7. Right Outer Join");
            System.out.println("8. Full Outer Join");
            System.out.println("9. Sair");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Digite o nome da tabela");
                    String tableName1 = scanner.nextLine();
                    System.out.println("Digite a chave a ser buscada");
                    String key1 = scanner.nextLine();
                    database.search(tableName1, key1);
                    break;

                case "2":
                    System.out.println("Digite o nome da tabela");
                    String tableName2 = scanner.nextLine();
                    System.out.println("Digite a chave a ser removida");
                    String key2 = scanner.nextLine();
                    database.remove(tableName2, key2);
                    break;

                case "3":
                    System.out.println("Digite o nome da tabela");
                    String tableName3 = scanner.nextLine();
                    database.countAll(tableName3);
                    break;

                case "4":
                    System.out.println("Digite o nome da tabela");
                    String tableName4 = scanner.nextLine();
                    System.out.println("Digite o campo");
                    String campo = scanner.nextLine();
                    System.out.println("Digite o valor");
                    String value = scanner.nextLine();
                    database.countWhere(tableName4, campo, value);
                    break;

                case "5":
                    System.out.println("Digite o nome da primeira tabela");
                    String tableName5 = scanner.nextLine();
                    System.out.println("Digite o nome da segunda tabela");
                    String tableName6 = scanner.nextLine();
                    database.innerJoin(tableName5, tableName6);
                    break;

                case "6":
                    System.out.println("Digite o nome da primeira tabela");
                    String tableName7 = scanner.nextLine();
                    System.out.println("Digite o nome da segunda tabela");
                    String tableName8 = scanner.nextLine();
                    database.leftJoin(tableName7, tableName8);
                    break;

                case "7":
                    System.out.println("Digite o nome da primeira tabela");
                    String tableName9 = scanner.nextLine();
                    System.out.println("Digite o nome da segunda tabela");
                    String tableName10 = scanner.nextLine();
                    database.rightJoin(tableName9, tableName10);
                    break;

                case "8":
                    System.out.println("Digite o nome da primeira tabela");
                    String tableName11 = scanner.nextLine();
                    System.out.println("Digite o nome da segunda tabela");
                    String tableName12 = scanner.nextLine();
                    database.fullJoin(tableName11, tableName12);
                    break;

                case "9":
                    sair = true;
                default:
                    System.out.println("Opção inválida");
                    break;

            }
        }
    }
}
