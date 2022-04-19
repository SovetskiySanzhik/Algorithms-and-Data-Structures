package data_structures.java.com;

public class MyLinkedList<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList(){}
    public T get(int index)
    {
        Node<T> current=head;
        for(int i=0;i<index;i++)
        {
            current=current.next;
        }
        return current.data;
    }
    public void add(T newItem)
    {
        Node<T> newNode=new Node<>(newItem);
        if(head==null)
        {
            head=tail=newNode;
        }else{
            tail.next=newNode;
            tail=newNode;
        }
        size++;
    }
    public void add(T newItem, int index)
    {
        Node<T> newNode=new Node<>(newItem);
        Node<T> current=head;
        if(index>=size)
        {
            tail.next=newNode;
            tail=newNode;
        }else{
        if(head==null)
        {
            head=tail=newNode;
        }else{
            for(int i=0;i<index-1;i++)
            {
                current=current.next;
            }
            newNode.next=current.next;
            current.next=newNode;
        }
        }
        size++;
    }
    public int find(T keyItem)
    {
        int i;
        Node<T> current=head;
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
    public T remove(int index)
    {
        Node<T> current=head;
        T key;
        for(int i=0;i<index-1;i++)
        {
            current=current.next;
        }
        key=current.next.data;
        current.next=current.next.next;
        size--;
        return key;
    }
    public void reverse()
    {
        Node<T> curr = head;
        Node<T> pre = null;
        Node<T> incoming = null;

        while(curr != null) {
            incoming = curr.next;   // store incoming item

            curr.next = pre;        // swap nodes
            pre = curr;             // increment also pre

            curr = incoming;        // increment current
        }

        head = pre; // pre is the latest item where
        // curr is null
    }



    private static class Node<E>
    {
        E data;
        Node<E> next;

        Node(E data)
        {
            this.data=data;
        }
    }

    @Override
    public String toString() {
        String result = "[ ";
        Node<T> current = head;

        for(int i=0;i<size;i++)
        {
            result += current.data+" ";
            current=current.next;
        }
        result+="]";
        return result;
    }
}
