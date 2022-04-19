package tests.java.com;
import data_structures.java.com.MyArrayList;

public class MyArrayListTest {
    public static void main(String[] args)
    {
        MyArrayList<Integer> MyArraylist=new MyArrayList<>();
        for(int i=0;i<10;i++)
        {
            MyArraylist.add((int)(Math.random() * 30));
        }
        System.out.println(MyArraylist+" - MyArrayList");
        MyArraylist.add(69,9);
        System.out.println(MyArraylist+" - add(69,9);");
        int temp=MyArraylist.remove(9);
        System.out.println(MyArraylist+" - remove(9); return = "+temp);
        temp =MyArraylist.find(10);
        System.out.println(MyArraylist+" - find(10); return = "+temp);
        MyArraylist.reverse();
        System.out.println(MyArraylist+" - reverse");
        System.out.println("-------------------------------------------------------------");
    }
}
