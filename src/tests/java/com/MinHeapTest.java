package tests.java.com;
import data_structures.java.com.MinHeap;

public class MinHeapTest {
    public static void main(String[] args)
    {
        MinHeap<Integer> heap=new MinHeap<>();
        System.out.println(heap + " - Min Heap");
        System.out.println("heap.empty() - "+heap.empty());
        for(int i=0;i<10;i++)
        {
            heap.insert((int) (Math.random() * 30));
        }
        System.out.println(heap+" - inserted(10 times) "+" size="+heap.size());
        System.out.println("heap.empty() - "+heap.empty());
        System.out.println("heap.getMin() - "+heap.getMin());
        System.out.println(heap + " - heap.extractMin()"+" result="+heap.extractMin());
        System.out.println(heap+" - heap.remove(5) "+"deleted="+heap.remove(5));
    }
}
