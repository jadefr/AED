
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ImportData {

    List<EntryCreateTable> tabela = new ArrayList<>();
    List<EntryData> registro = new ArrayList<>();
    List<EntryKey> chave = new ArrayList<>();

    final static Charset ENCODING = StandardCharsets.ISO_8859_1;
    BufferedReader reader;

    public ImportData(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        reader = Files.newBufferedReader(path, ENCODING);

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("CREATE TABLE")) {
                String linha = line;
                while ((line = reader.readLine()) != null) {
                    linha = linha.concat(line);
                    if (line.equals(");")) {
                        break;
                    }
                }

                StringTokenizer lineTokenizer = new StringTokenizer(linha, " ");
                lineTokenizer.nextToken();
                lineTokenizer.nextToken();
                String tableName = lineTokenizer.nextToken(); // guarda o nome da tabela
                int x = linha.indexOf("(");
                String y = linha.substring(x + 1);
                lineTokenizer = new StringTokenizer(y, ",");

                List<String> headers = new ArrayList<>();
                while (lineTokenizer.hasMoreTokens()) {
                    String yy = lineTokenizer.nextToken();
                    int i = 0;
                    while (i < yy.length()) {
                        if (yy.charAt(i) != ' ') {
                            i++;
                        } else {
                            break;
                        }
                    }
                    String xy = yy.substring(i);
                    StringTokenizer xyTokenizer = new StringTokenizer(xy, " ");
                    String header = xyTokenizer.nextToken();
                    headers.add(header); // guarda o vetor de header
                }

                tabela.add(new EntryCreateTable(tableName, headers.toArray(new String[headers.size()])));
            }

            

            if (line.contains("COPY")) {
                
                String dataLine;
                String linha = line;
                dataLine = line;

                StringTokenizer lineTokenizer = new StringTokenizer(linha, " ");
                lineTokenizer.nextToken();
                String tableName = lineTokenizer.nextToken(); // guarda o nome da tabela

                int x = linha.indexOf("(");
                int y = linha.indexOf(")");
                String xx = linha.substring(x + 1, y);
                List<String> headers = new ArrayList<>();
                StringTokenizer headerTokenizer = new StringTokenizer(xx, ", ");
                while (headerTokenizer.hasMoreTokens()) {
                    String header = headerTokenizer.nextToken();
                    headers.add(header); // guarda o vetor de headers
                }

                while ((line = reader.readLine()) != null && !line.equals("\\.")) {
                    EntryData data = new EntryData(tableName, headers.toArray(new String[headers.size()]), line.split("\t"));
                    registro.add(data);
                }
            }

            if (line.contains("ALTER TABLE")) {
                String linha = line;
                while ((line = reader.readLine()) != null) {
                    linha = linha.concat(line);
                    if (line.contains(";")) {
                        break;
                    }
                }
                if (linha.toLowerCase().contains("primary key")) {

                    StringTokenizer lineTokenizer = new StringTokenizer(linha, " ");
                    lineTokenizer.nextToken();
                    lineTokenizer.nextToken();
                    lineTokenizer.nextToken();
                    String tableName = lineTokenizer.nextToken(); // guarda o nome da tabela
                    int x = linha.indexOf("(");
                    int y = linha.indexOf(")");
                    String xx = linha.substring(x + 1, y);
                    StringTokenizer headerTokenizer = new StringTokenizer(xx, ", ");
                    List<String> headers = new ArrayList<>();
                    while (headerTokenizer.hasMoreTokens()) {
                        String header = headerTokenizer.nextToken();
                        headers.add(header); // guarda os campos correspondetes às chaves primárias numa lista
                    }

                    chave.add(new EntryKey(tableName, headers.toArray(new String[headers.size()])));
                }
            }

        }

    }

}
