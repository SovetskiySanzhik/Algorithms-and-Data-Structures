package data_structures.java.com;


public class MyHashTable<K, V>
{
    private Object[] chainArray;//массив хранащий головы связанных листов: сделан именно Object потому что должен быть способным хранить HashNode<K,V>
    private int M=10;//количество связанный списков (или чисто технически длина массива  chainArray)
    private int size;// если M количество связанных списков то size сумарное количество узлов в этих списках
    public MyHashTable()
    {
        chainArray=new Object[this.M];
    }//конструктор иннициализирует пустой chainArray с длинной дефолтной M
    public MyHashTable(int M)
    {
        chainArray=new Object[M];
    }//такой же конструктор но с выборочной M
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private int hash(K key)
    {
        return (key.hashCode() &0x7fffffff)%M;
    }// хеш функций как из лекций использует с себе M так как должна все всегда равномерно распределять узлы даже если M изменяется
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = (HashNode<K, V>)chainArray[index];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    public V remove(K key) {//функция удаления из hashtable по ключу
        int index = hash(key);//переводм ключ в хеш код(или индекс связанного списка в котором находится этот key)
        HashNode<K, V> head = (HashNode<K, V>)chainArray[index];//создаем голову того связанного списка в котором находится key
        if (head == null) {//проверка на случай если этой головы нет
            return null;
        }
        if (head.key.equals(key)) {//проверка на случай если ключ головы и ключ который мы ищем одинаковы
            V val = head.value;
            head = head.next;
            chainArray[index]= head;
            size--;
            return val;
        } else {
            HashNode<K, V> prev = null;// нужно так как у нас нету prev и используется для удаления
            while (head != null) {// while чтобы дойти до конца связанного списка
                if (head.key.equals(key)) {// проверяем ключ каждого элемента связанного списка на равность тому который мы ищем
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
    public void put(K key, V value) {//функция для вставки новой пары ключ-значений в hash table
        int index = hash(key);//переводм ключ в хеш код(или индекс связанного списка в котором находится этот key)
        HashNode<K, V> head = (HashNode<K, V>)chainArray[index];//создаем голову того связанного списка в котором находится key
        HashNode<K, V> newNode = new HashNode<>(key,value);//создаем новый узел с указанными ключөзначение
        if (head == null) {//на случай если головы нет
            chainArray[index]= newNode;
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
            if (head == null) {//это на тот случай если нет
                head = (HashNode<K, V>)chainArray[index];// возвращаемся в начало связанного списка
                newNode.next = head;// тут запихиваем новый узел в начало связанного списка а не в конец
                //(это сделанно для ээфективности поиска ведь лучше чтобы значений находлились не в конце
                // связанно списка иначе при поиске придется его полность пройти что приведет к времени O(n))
                chainArray[index] =newNode;
                size++;
            }
        }
        if (((1.0 * size) / M )> 1) {// это нужна на тот случий когда наш массив chainArray запонен на 75% его размер увеличится
            //нужно увеличивать размер массива за тем чтобы связанные списки не были слишком длинными и было равномерное распеделение
            Object[] tmp = chainArray;
            int tmpM=M;
            M = 2 * M;
            chainArray = new Object[M];
            HashNode<K, V> headNode;
            for(int i=0;i<tmpM;i++)//перезаписывем значения в новый массив с увелечинным M
            {
                headNode=(HashNode<K, V>)tmp[i];
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
    private static class HashNode<K,V>// внутренний или вложенный класс узлов использвуемый для реализаций связанных списков в hash tablr
    {
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
            return "--> {"+key+"-"+value+"}";
        }//функция для вывода узлов
    }

    @Override
    public String toString()//функция для вывода всей хэш талицы
    {
        String str="ARRAY    KEY-VALUE\n";
        HashNode<K,V> current;

        for (int i=0;i<M;i++)
        {
            str+=i+"   ";
            current= (HashNode<K,V>)chainArray[i];
            while(current != null)
            {
                str+=" "+current.toString();
                current = current.next;
            }
            str+="\n";
        }
        str+="M="+M;
        return str;
    }
}

