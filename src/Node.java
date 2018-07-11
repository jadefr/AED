
public class Node {

    int index;
    char character;
    Node left;
    Node right;
    Node parent;
    String[] data;
    String key;

    public Node(Node parent, int index, char compare) { //nó pai
        this.parent = parent;
        this.index = index;
        setCharacter(compare);
    }

    public Node(Node parent, String[] data, String key) {  //nó folha
        this.parent = parent;
        setData(data);
        this.key = key;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    public void setParent(Node parent){
        this.parent = parent;
    }
    public Node getParent(){
        return parent;
    }
}
