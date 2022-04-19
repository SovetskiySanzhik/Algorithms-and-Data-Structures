package algorithms.java.com;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
public class Test
{
    public static void main(String[] args)
    {
        try (Scanner scn = new Scanner(System.in)) {
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4,4,4,5,6));
            System.out.println("изначальный: "+arr);
            count_sort(arr);
            System.out.println("counting sort: "+arr);
            quick_sort(arr, 0, arr.size() - 1);
            System.out.println("quick sort: "+arr);
            merge_sort(arr, 0, arr.size() - 1);
            System.out.println("merge sort: "+ arr);
            insertion_sort(arr);
            System.out.println("по увеличению: "+arr);
            insertion_reverse_sort(arr);
            System.out.println("по уменьшению: "+arr);
            heap_sort(arr);
            System.out.println("heap sort: " + arr);
            System.out.println("Введи число которое хочешь найти ");
            int number=scn.nextInt();
            int x=binarySearch(arr,0,arr.size()-1,number);//прежде чем юзать этот поиск надо чтобы он был по увелечению
            System.out.println("индекс этого числа = "+x);
            number=scn.nextInt();
            x=binarySearch(arr,0,arr.size()-1,number);
            System.out.println("индекс этого числа = "+x);
        }

    }
    public static int binarySearch(ArrayList<Integer> arr, int l, int r, int x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) == x)
                return mid;
            if (arr.get(mid) > x)
                return binarySearch(arr, l, mid - 1, x);
            return binarySearch(arr, mid + 1, r, x);
        }else       
            return -1;
    }
    

    public static void heap_sort(ArrayList<Integer> arr)
    {

        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--)heapify(arr, n, i);//переделывается в вид масимальной кучи

        for (int i=n-1; i>=0; i--)//берется начальный элемент (максимальный после heapify)массива и ставится в конец
        {
            int temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);//процесс замены
            heapify(arr, i, 0);//перевызывается heapify с новой длиной массива i и делает эту часть снова max кучей
            //и ставится ноль потому что с этого места и начнутся проблемы в max куче
        }
    }
    public static void heapify(ArrayList<Integer> arr, int n, int i)
    {
        //эта функия переделывает массив arr по длину n в вид max кучи
        int largest = i;  // i это позиция элемента в корне который надо сделать в вид  max кучи
        int l = 2*i + 1;  // тот что слева внизу в древе
        int r = 2*i + 2;  // тот что справа

        // если левый больше самого большого
        if (l < n && arr.get(l) > arr.get(largest)) largest = l;

        // если правый
        if (r < n && arr.get(r) > arr.get(largest)) largest = r;

        // если самый большой и так на i то этот блок пропускается потому что ничего менять не надо а если нет то надо
        if (largest != i)
        {
            int swap = arr.get(i);//процесс постановки самого большого в корень
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);
            heapify(arr, n, largest);//так как мы поменяли местами мы могли изменить следующие лепестки поэтому
            //это функция будет провторятся до тех пока все в линий не будет в виду max heap
        }
    }

    public static void insertion_sort(ArrayList<Integer> arr)

    {
        int j,i,key;
        for(i=1;i<arr.size();i++)
        {
            key= arr.get(i);
            j=i-1;
            while(j>=0&& arr.get(j) >key)
            {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
        }
    }

    public static void insertion_reverse_sort(ArrayList<Integer> arr)
    {
        int j,i,key;
        for(i=arr.size()-2;i>=0;i--)
        {
            key= arr.get(i);
            j=i+1;
            while(j< arr.size()&& arr.get(j) >key)
            {
                arr.set(j-1 , arr.get(j));
                j++;
            }
            arr.set(j-1, key);
        }
    }

    public static void merge(ArrayList<Integer> arr, int l, int m, int r)
    {
        // Merges two subarrays of arr[].
        // First subarray is arr[l..m]
        // Second subarray is arr[m+1..r]
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr.get(l + i);
        for (int j = 0; j < n2; ++j)
            R[j] = arr.get(m + 1 + j);

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr.set(k, L[i]);
                i++;
            }
            else {
                arr.set(k, R[j]);
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr.set(k, L[i]);
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr.set(k, R[j]);
            j++;
            k++;
        }
    }
    public static void merge_sort(ArrayList<Integer> arr, int l, int r)
    {
        // Main function that sorts arr[l..r] using
        // merge()
        if (l < r)
        {
            // Find the middle point
            int m =l+ (r-l)/2;

            // Sort first and second halves
            merge_sort(arr, l, m);
            merge_sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    public static int partition(ArrayList<Integer> arr, int low, int high)
    {
        // pivot
        int pivot = arr.get(high);

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {

            // If current element is smaller
            // than the pivot
            if (arr.get(j) < pivot)
            {

                // Increment index of
                // smaller element
                i++;
                Collections.swap(arr, i, j);
            }
        }
        Collections.swap(arr, i + 1, high);
        return (i + 1);
    }
    public static void quick_sort(ArrayList<Integer> arr, int low, int high)
    {
        if (low < high)
        {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quick_sort(arr, low, pi - 1);
            quick_sort(arr, pi + 1, high);
        }
    }
    static void count_sort(ArrayList<Integer> arr)
    {
        int min = Collections.min(arr);
        int max=Collections.max(arr);
        int range = max- min + 1;//радиус чисел
        int[] count = new int[range];//этот массив нужен для расчетов
        int[] output = new int[arr.size()];

        for (int i = 0; i < arr.size(); i++) //этот пункт поставит количество чисел из arr в массив count
        {
            count[arr.get(i) - min]++;
        }

        for (int i = 1; i < count.length; i++) //вот тут произойдет та странная приплюсовка
        {
            count[i] += count[i - 1];
        }

        for (int i = arr.size() - 1; i >= 0; i--) //тут произходит передача из массива count результатов в output
        {
            output[count[arr.get(i) - min] - 1] = arr.get(i);
            count[arr.get(i) - min]--;
        }

        for (int i = 0; i < arr.size(); i++) //тут произходит перенос из массива output в наш массив или уже сортировка
        {
            arr.set(i, output[i]);
        }
    }
}

