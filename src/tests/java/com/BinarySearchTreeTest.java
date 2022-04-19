package tests.java.com;
import data_structures.java.com.BinarySearchTree;

public class BinarySearchTreeTest {
    public static void main(String[] args)
    {
        BinarySearchTree<Integer,String> bst = new BinarySearchTree<>();
        bst.put(1,"one");
        bst.put(2,"two");
        bst.put(3,"three");
        bst.put(4,"three");
        bst.put(5,"three");
        bst.put(5,"three");
        bst.put(6,"three");
        System.out.println(bst);
        System.out.println(bst.get(6));
        bst.remove(4);
        System.out.println(bst);
    }
}
