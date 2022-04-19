package tests.java.com;
import data_structures.java.com.MyLinkedList;

public class MyLinkedListTest {
    public static void main(String[] args)
    {
        MyLinkedList<Integer> lin=new MyLinkedList<>();
        for(int i=0;i<10;i++)
        {
            lin.add((int) Math.round((Math.random() * 30) - 15));
        }
        System.out.println(lin+" - MyLinkedList");
        lin.add(69,5);
        System.out.println(lin +" - add(69,5);");
        int temp=lin.find(69);
        System.out.println(lin+" - find(69) , return = "+temp);
        temp=lin.remove(5);
        System.out.println(lin+" - remove(5) , return = "+temp);
        lin.reverse();
        System.out.println(lin+" - reverse");
        System.out.println("-------------------------------------------------------------");
    }
}
