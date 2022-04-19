package data_structures.java.com;

public class MyDoublyLinkedList<T>
{
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;
    public MyDoublyLinkedList(){}

    public void add(T newItem)
    {
        MyNode<T> newNode=new MyNode<>(newItem);
        if(head==null)
        {
            head=tail=newNode;
        }else{
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
            tail.next = null;
        }
        size++;
    }
    public void add(T newItem, int index)
    {
        MyNode<T> newNode=new MyNode<>(newItem);
        MyNode<T> current=head;
        if(index>=size)
        {
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
            tail.next = null;
        }else{
            if(head==null)
            {
                head=tail=newNode;
            }else{
                for(int i=0;i<index;i++)
                {
                    current=current.next;
                }
                current.prev.next=newNode;
                newNode.prev=current.prev;
                newNode.next=current;
                current.prev=newNode;
            }
        }
        size++;
    }
    public T remove(int index)
    {
        MyNode<T> current=head;
        T key;
        for(int i=0;i<index;i++)
        {
            current=current.next;
        }
        key=current.data;
        current.prev.next=current.next;
        current.next.prev=current.prev;
        size--;
        return key;
    }
    public int find(T keyItem)
    {
        int i;
        MyNode<T> current=head;
        for(i=0;i<size;i++)
        {
            if(current.data==keyItem)
            {
                return i;
            }
            current=current.next;
        }
        return -1;
    }
    public void reverse()
    {
        MyNode<T> temp = null;
        MyNode<T> current = head;

        /* swap next and prev for all nodes of
         doubly linked list */
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        /* Before changing head, check for the cases like
         empty list and list with only one node */
        if (temp != null) {
            head = temp.prev;
        }
    }


    private static class MyNode<E>
    {
        E data;
        MyNode<E> next=null;
        MyNode<E> prev=null;
        MyNode(E data)
        {
            this.data=data;
        }
    }
    public String toString(){
        String result = "[ ";
        MyNode<T> current = head;

        for(int i=0;i<size;i++)
        {
            result += current.data+" ";
            current=current.next;
        }
        result+="]";
        return result;
    }
}
