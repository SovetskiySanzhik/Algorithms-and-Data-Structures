package data_structures.java.com;

public class MyArrayList<T>
{
    private Object[] array;
    private int size=0;
    private int capacity=5;

    public MyArrayList()
    {
        array=new Object[capacity];
    }
    public T get(int index)
    {
        return (T)array[index];
    }
    public void add(T newItem)
    {
        if(size==capacity)
        {
            increaseBuffer();
        }
        array[size++]=newItem;
    }
    private void increaseBuffer()
    {
        capacity=(int)(capacity*1.5);
        Object[] array2=new Object[capacity];
        for (int i=0;i<size;i++)
        {
            array2[i]=array[i];
        }
        array=array2;
    }
    public int getSize()
    {
        return size;
    }

    public void add(T newItem, int index)
    {
        if(size==capacity)
        {
            increaseBuffer();
        }
        for (int i=size+1;i>index;i--)
        {
            array[i]=array[i-1];
        }
        array[index]=newItem;
        size++;
    }

    public int find(T keyItem)
    {
        for(int i=0;i<size;i++)
        {
            if(array[i]==keyItem)
            {
                return i;
            }
        }
        return -1;
    }
    public T remove(int index)
    {
        T key=(T)array[index];
        for (int i=index;i<size-1;i++)
        {
            array[i]=array[i+1];
        }
        size--;
        return key;
    }
    public void reverse()
    {
        T t;
        int i, k ;
        for (i = 0; i < size / 2; i++)
        {
            t = (T)array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = t;
        }
    }

    @Override
    public String toString()
    {
        String str="[ ";

        for (int i=0;i<size;i++)
        {
            str+=array[i]+" ";
        }
        str+="]";
        return str;
    }
}
