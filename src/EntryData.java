
public class EntryData {
	String chave;
	String tableName;
	String[] headers;
	String[] data;
	
	public EntryData(String tableName, String[] headers, String[] data){
		this.tableName = tableName;
		this.headers = headers;
		this.data = data;
	}

}
