package data_structures.java.com;

public class MinHeap<T extends Comparable<T>>
{
    private Object[] array;
    private int size=0;
    private int capacity=5;

    public MinHeap()
    {
        array=new Object[capacity];
    }
    public void buildHeap()
    {

        for (int i = size / 2; i >= 0; i--)
        {
            minHeapify(i);
        }
    }
    private void increaseBuffer()
    {
        if(size==capacity)
        {
            capacity=(int)(capacity*1.5);
            Object[] array2=new Object[capacity];
            for (int i=0;i<size;i++)
            {
                array2[i]=array[i];
            }
            array=array2;
        }
    }
    private int leftChildInd(int index)
    {
        return (2*index)+1;
    }
    private int rightChildInd(int index)
    {
        return (2*index)+2;
    }
    private int parent(int index)
    {
        return (index - 1) / 2;
    }
    private void swap(int index1, int index2) {
        Object temp;
        temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    private void minHeapify(int index)
    {
        int left = leftChildInd(index);
        int right = rightChildInd(index);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= size - 1 && get(index).compareTo(get(left)) > 0) {
            smallest = left;
        } else {
            smallest = index;
        }

        if (right <= size - 1 && get(smallest).compareTo(get(right)) > 0) {
            smallest = right;
        }

        // if the smallest key is not the current key then bubble-down it.
        if (smallest != index) {
            swap(index, smallest);
            minHeapify(smallest);
        }
    }
    private void set(int index,T newItem)
    {
        array[index]=newItem;
    }

    public T getMin()
    {
        return get(0);
    }
    public T get(int index)
    {
        return (T) array[index];
    }
    public int size()
    {
        return size;
    }
    public boolean empty()
    {
        return size == 0;

    }
    public void insert(T newItem)
    {
        increaseBuffer();
        array[size++]=newItem;
        int i = size - 1;
        int parent = parent(i);

        while (parent != i && get(parent).compareTo(get(i))>0)
        {
            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    public T remove(int index)
    {
        T key=(T)array[index];
        for (int i=index;i<size-1;i++)
        {
            array[i]=array[i+1];
        }
        size--;
        buildHeap();
        return key;
    }
    public T extractMin()
    {
        if (size == 0)
        {
            throw new IllegalStateException("MinHeap is EMPTY");
        }
        // remove the min item ,and set it as new root
        return remove(0);
    }

    @Override
    public String toString() {
        String str="[";

        for (int i=0;i<size;i++)
        {
            if (i != size - 1)
            {
                str+=array[i]+", ";
            }else{
                str+=array[size-1];
            }
        }
        str+="]";
        return str;
    }
}
