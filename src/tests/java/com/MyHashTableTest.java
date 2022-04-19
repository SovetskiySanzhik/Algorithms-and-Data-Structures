package tests.java.com;

import data_structures.java.com.MyHashTable;
public class MyHashTableTest {
    public static void main(String[] args)
    {
        MyHashTable<Integer,String> t1=new MyHashTable<>();
        t1.put(1,"one");
        t1.put(2,"two");
        t1.put(3,"three");
        t1.put(4,"four");
        t1.put(5,"five");
        t1.put(55,"fout");
        for(int i=6;i<11;i++)
        {
            t1.put(i,"number");
        }
        t1.put(12312,"smt");
        System.out.println(t1);
        t1.remove(5);
        System.out.println(t1.get(55));
    }
}
