package tests.java.com;

import data_structures.java.com.HashTable;
public class HashTableTest {
    public static void main(String[] args)
    {
        //HashTable
        HashTable<Integer,String> huh=new HashTable<>();
        huh.put(1,"one");
        huh.put(2,"two");
        huh.put(3,"three");
        huh.put(4,"four");
        huh.put(5,"five");
        huh.put(55,"fout");
        for(int i=6;i<11;i++ )
        {
            huh.put(i,"number");
        }
        huh.put(12312,"smt");
        System.out.println(huh);
        System.out.println("get(55) = "+huh.get(55));
        System.out.println("SIZE = "+ huh.getSize());
    }
}
