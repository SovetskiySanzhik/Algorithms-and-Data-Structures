package data_structures.java.com;

import java.util.ArrayList;

public class HashTable<K, V> {
    private ArrayList<HashNode<K, V>> chainArray;
    private int M=10;
    private int size=0;
    public HashTable()
    {
        chainArray = new ArrayList<>();
        // Create empty chains
        for (int i = 0; i < M; i++)
            chainArray.add(null);
    }
    public int getSize()
    {
        return size;
    }
    private int hash(K key)
    {
        return (key.hashCode() &0x7fffffff)%M;// М нужно чтобы было равное перераспределение когда М увеличиваем
    }
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray.get(index);
        if (head == null) {//проверка на случай если этой головы нет
            return null;
        }
        if (head.key.equals(key)) {//на случай если ключ головы и ключ который мы ищем одинаковы
            V val = head.value;
            head = head.next;
            chainArray.set(index, head);
            size--;
            return val;
        } else {
            HashNode<K, V> prev = null;// нужно так как у нас нету prev и используется для удаления
            while (head != null) {
                if (head.key.equals(key)) {// проверяем ключ каждого элемента связанного списка на равность ключу тому который мы ищем
                    prev.next = head.next;
                    size--;
                    return head.value;
                }
                prev = head;
                head = head.next;
            }
            return null;
        }
    }
    public void put(K key, V value) {
        int index = hash(key);//переводм ключ в хеш код(или индекс связанного списка в котором находится этот key)
        HashNode<K, V> head = chainArray.get(index);
        HashNode<K, V> newNode = new HashNode<>(key,value);
        if (head == null) {//на случай если головы нет
            chainArray.set(index, newNode);
            size++;
        } else {
            while (head != null) {//это на случай если такой ключ уже был и оно просто обновится его значений
                if (head.key.equals(key)) {
                    head.value = value;
                    size++;
                    break;
                }
                head = head.next;
            }
            if (head == null) {//это на тот случай если это новый ключ
                head =chainArray.get(index);
                newNode.next = head;


                chainArray.set(index, newNode);
                size++;
            }
        }
        if (((1.0 * size) / M )> 0.90) {//ТУт стоит 0.9 просто затем чтобы не М не становилось огромным(условно 40)
             //при всего лишь 10 ключ-значений знаю что тут должно быть 0.75
            ArrayList<HashNode<K, V>> tmp = chainArray;
            int tmpM=M;
            M *=1.5;//так же умножаю только на полтора чтобы М не становилось слишком большой
            size=0;
            chainArray= new ArrayList<>(M);
            for (int i = 0; i < M; i++) chainArray.add(null);
            HashNode<K, V> headNode;
            for(int i=0;i<tmpM;i++)
            {
                headNode=tmp.get(i);
                while (headNode != null) {
                    put(headNode.key,headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
    class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K,V> next;

        public HashNode(K key,V value)
        {
            this.key=key;
            this.value=value;
        }
        @Override
        public String toString() {
            return "--> {"+key+":"+value+"}";
        }
    }
    @Override
    public String toString()
    {
        String str="HASHTABLE    KEY-VALUE\n";
        HashNode<K,V> current;
        for (int i=0;i<M;i++)
        {
            str+=i+"      ";
            current= (HashNode<K,V>) chainArray.get(i);
            while(current != null)
            {
                str+=" "+current.toString();
                current = current.next;
            }
            str+="\n";
        }
        str+="M = "+M;
        return str;
    }
}