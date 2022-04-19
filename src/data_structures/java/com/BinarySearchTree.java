package data_structures.java.com;

// бинарное дерево реализуется с помошью узлов с ключом и значением
public class BinarySearchTree<K extends Comparable<K>,V>
{
    private Node root;// корень бинарного дерева или самая верхняя его часть
    public BinarySearchTree() {
        
    }
    public void put(K key,V value)//использую два put чтобы создать узел из ключа и значения и удобно ими пользоваться
    {
        root = put( new Node(key, value),root);
    }
    private Node put(Node update,Node node)//основой put который реализует логику
    {// put рекурсивный так как неизвестно насколько длиными могут быть листья бинарного дерева и лучше будет рекурсивной

        if (node == null) {//на случай если корня нет
                node = update;
            } else {
                if (update.key.equals(node.key)) {// если мы сразу нашли нужное место для ставки а точнее если оно совпала с корнем
                    node.value = update.value;

                } else if (update.key.compareTo(node.key) <= 0) {//если наше ключ-значение меньше корня
                    node.left = put(update, node.left);
                } else {//если больше
                    node.right = put(update, node.right);
                }
            }
        return node;
    }
    public V get(K key)
    {
        if(key == null){//если нам вели нул вместо ключа что нельзя делать
            throw new IllegalArgumentException("No element in the tree has a null key.");
        }else{
            return get(key, root);
        }
    }
    private V get(K key, Node node){
        if(node==null)//если корень пустой
        {
            return null;
        }else{
            K temp = node.key;
            if(key.equals(temp)){ //we have found the key
                return node.value;
            }else if(key.compareTo(temp) <= 0){ //look left
                return get(key, node.left);
            }else{                             //look right
                return get(key, node.right);
            }
        }
    }
    public void remove(K key) {
        root = remove(key, root);
    }
    private Node remove(K value, Node node) {
        if (node == null) {	// key not in tree корень пустой
            return null;
        }
        if (value.compareTo(node.key) == 0) { // remove this node
            if (node.left == null) { // replace this node with right child
                return node.right;
            } else if (node.right == null) { // replace with left child
                return node.left;
            } else {
                // replace the value in this node with the value in the
                // rightmost node of the left subtree
                node.key = getRightmost(node.left);
                // now remove the rightmost node in the left subtree,
                // by calling "remove" recursively
                node.left = remove(node.key, node.left);
                // return node;  -- done below
            }
        } else {		// remove from left or right subtree
            if (value.compareTo(node.key) < 0) {
                // remove from left subtree
                node.left = remove(value, node.left);
            } else {		// remove from right subtree
                node.right = remove(value, node.right);
            }
        }
        return node;
    }

    private K getRightmost(Node node) {
        assert(node != null);
        Node right = node.right;
        if (right == null) {
            return node.key;
        } else {
            return getRightmost(right);
        }
    }
    private class Node// супер важный класс узлов с ссылкой на левой и правый узел
    {
        private K key;
        private V value;
        private Node left,right;
        public Node(K key,V value)
        {
            this.key=key;
            this.value=value;
        }
    }
    @Override
    public String toString()//метод для вывода узлов бинарного дерева найденно в инете
    {
        StringBuilder builder = new StringBuilder();
        if (root == null)
            return "";
        builder.append(toString(root));
        return builder.toString();
    }

    // recursively printing out the nodes

    private String toString(Node r) {
        if (r == null)
            return "";
        else
            return r.key+":"+ r.value + " " + toString(r.left) + " " + toString(r.right);
    }
}