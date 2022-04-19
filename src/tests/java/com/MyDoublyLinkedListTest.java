package tests.java.com;

import data_structures.java.com.MyDoublyLinkedList;

public class MyDoublyLinkedListTest{
    public static void main(String[] args)
    {
        MyDoublyLinkedList<Integer> dl=new MyDoublyLinkedList<>();
        for(int i=0;i<10;i++)
        {
            dl.add((int) Math.round((Math.random() * 30) - 15));
        }
        System.out.println(dl+" - MyDoublyLinkedList");
        dl.add(69,5);
        System.out.println(dl +" - add(69,5);");
        int temp=dl.find(69);
        System.out.println(dl+" - find(69) , return = "+temp);
        temp=dl.remove(5);
        System.out.println(dl+" - remove(5) , return = "+temp);
        dl.reverse();
        System.out.println(dl+" - reverse");
        System.out.println("-------------------------------------------------------------");
    }
}
