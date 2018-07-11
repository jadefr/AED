
import java.util.ArrayList;
import java.util.List;

public class Patricia {

    Node root;
    int amount; // quantidade de registros

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void incrementAmount() {
        this.amount++;
    }

    public void insert(String[] data, String key) {
        setRoot(insert(getRoot(), null, data, key + "\0"));

    }

    public Node insert(Node node, Node parent, String[] data, String key) {
        if (node == null) {
            incrementAmount();
            return new Node(parent, data, key);
        } else {
            if (node.getKey() == null) { // nó pai

                if (node.getIndex() < key.length() && key.charAt(node.getIndex()) == node.getCharacter()) {
                    node.setLeft(insert(node.getLeft(), node, data, key));
                } else {
                    node.setRight(insert(node.getRight(), node, data, key));
                }
            } else { // nó folha
                if (!node.getKey().equals(key)) {
                    int diff = diffIndex(node.getKey(), key);
                    Node novo = new Node(parent, diff, node.getKey().charAt(diff)); //novo nó contém a posição e o caractere diferente do nó atual
                    novo.setLeft(node); // o pai vira filho esquerdo
                    novo.setRight(new Node(novo, data, key)); // o nó que se quer inserir vira filho direito
                    incrementAmount();
                    return novo;
                }
            }
        }
        return node;
    }

    public int diffIndex(String key1, String key2) {
        int i = 0;
        while (i < key1.length() && i < key2.length() && key1.charAt(i) == key2.charAt(i)) {
            i++;
        }
        return i; //primeira posição diferente
    }

    public void printNodes() {
        printNodes(getRoot());
    }

    public void printNodes(Node node) {
        if (node != null) {
            if (node.getKey() != null) {
                System.out.println(node.getKey());
            }
            printNodes(node.getLeft());
            printNodes(node.getRight());
        }
    }

    public Node search(String key) {
        if (key.charAt(key.length() - 1) == '\0') {
            return search(getRoot(), null, key);
        } else {
            return search(getRoot(), null, key + "\0");
        }
    }

    private Node search(Node node, Node parent, String key) {
        if (node != null) {
            if (node.getKey() == null) {
                char c = node.getCharacter();
                int position = node.getIndex();
                if (position < key.length() && key.charAt(position) == c) {
                    return search(node.getLeft(), node, key);
                } else {
                    return search(node.getRight(), node, key);
                }
            } else {
                if (node.getKey().equals(key)) {
                    return node;
                }
            }
        }
        return null;
    }

    public void clean() {
        setRoot(null);
        setAmount(0);
    }

    public List<Node> inOrder() {
        return inOrder(getRoot());
    }


    
    private List<Node> inOrder(Node root) {
        List<Node> folhas = new ArrayList<>();
        if (root == null) {
            return folhas;
        }
        folhas.addAll(inOrder(root.getLeft()));
        if (root.getKey() != null) { //folha
            folhas.add(root);
        }
        folhas.addAll(inOrder(root.getRight()));
        return folhas;
    }
    

    public Node getBrother(Node node, Node parent) { // método para saber se o nó a ser removido é filho esquerdo ou direito
        if (parent.getLeft() == node) {
            parent.setLeft(null);
            return parent.getRight();
        }
        if (parent.getRight() == node) {
            parent.setRight(null);
            return parent.getLeft();
        }
        return null;
    }

    public void setFather(Node parent, Node brother) { // método para setar o irmão do nó removido como pai
        if (parent.getParent() != null) {
            Node grandparent = parent.getParent();
            if (grandparent.getLeft() == parent) {
                grandparent.setParent(brother);
            }
            if (grandparent.getRight() == parent) {
                grandparent.setRight(brother);
            }
        }
    }

    public Node remove(String key) {
        if (key.charAt(key.length() - 1) == '\0') {
            return remove(getRoot(), null, key);
        } else {
            return remove(getRoot(), null, key + "\0");
        }
    }

    public Node remove(Node node, Node parent, String key) {
        if (node != null) {
            if (node.getKey() == null) { // nó pai
                char c = node.getCharacter();
                int position = node.getIndex();
                if (position < key.length() && key.charAt(position) == c) {
                    return remove(node.getLeft(), node, key);
                } else {
                    return remove(node.getRight(), node, key);
                }
            } else {
                if (node.getKey().equals(key)) { //nó folha
                    Node brother = getBrother(node, parent);
                    setFather(parent, brother);
                }
            }
        }
        return null;
    }
    //private List<Node> inOrder(Node root) {    // pra imprimir o percurso
    //    List<Node> folhas = new ArrayList<>();
    //    if (root == null) {
    //        return folhas;
    //    }
    //    if (root.getRight() != null) {
    //    System.out.println("Chave: " + root.getKey());
    //    if (root.getLeft() != null) {
    //        System.out.println("Esquerda: " + root.getLeft().getKey());
    //    } else {
    //        System.out.println("Esquerda: nulo");
    //    }
    //
    //        System.out.println("Direita: " + root.getRight().getKey());
    //    } else {
    //        System.out.println("Direita: nulo");
    //    }
    //
    //    System.out.println("---------------");
    //    folhas.addAll(inOrder(root.getLeft()));
    //    if (root.getKey() != null) { //folha
    //        folhas.add(root);
    //    }
    //    folhas.addAll(inOrder(root.getRight()));
    //    return folhas;
    //}
}
