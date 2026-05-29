
import java.util.*;
public class Sorting
{
   public static void main (String[] args)
   {
      ArrayList<Integer> list = new ArrayList<Integer>();
      Random rand = new Random();
      for(int i = 0; i <= 10; i++)
      {
         list.add(rand.nextInt(10));
      }
      Integer[] arr = list.toArray(new Integer[list.size()]);
      Integer[] arr2 = Arrays.copyOf(arr, arr.length);
      Integer[] arr3 = Arrays.copyOf(arr, arr.length);
      Integer[] arr4 = Arrays.copyOf(arr, arr.length);
      Integer[] arr5 = Arrays.copyOf(arr, arr.length);
      Integer[] arr6 = Arrays.copyOf(arr, arr.length);
      Integer[] arr7 = Arrays.copyOf(arr, arr.length);
      sort(arr7);
      heapSort(arr6);
      mergeSort(arr5);
      shellSort(arr4);
      insertionSort(arr3);
      bubbleSort(arr2);
      selectionSort(arr);
      
   }

   //Quick sort
   public static <T extends Comparable<T>> void sort(T[] table)
   {
      int[] count = new int[2];
      quickSort(table, 0, table.length - 1, count);
      
      System.out.print("QuickSort ");
      System.out.println(Arrays.toString(table));
      System.out.println("Comparisons: " + count[0]);
      System.out.println("Exchanges: " + count[1]);
   }
   private static <T extends Comparable<T>> void quickSort(T[] table, int first, int last, int[] count)
   {
      if(first < last)
      {
         int pivIndex = partition(table, first, last, count);
         
         quickSort(table, first, pivIndex - 1, count);
         
         quickSort(table, pivIndex + 1, last, count);
      }
   }
   private static <T extends Comparable<T>> int partition(T[] table, int first, int last, int[] count)
   {
      T pivot = table[first];
      int up = first;
      int down = last;
      do
      {
         while((up < last) && (pivot.compareTo(table[up]) >= 0))
         {
            up++;
            count[0]++;
         }
         while(pivot.compareTo(table[down]) < 0)
         {
            down--;
            count[0]++;
         }
         if(up < down)
         {
            swap(table, up, down);
            count[1]++;
         }
      }
      while(up < down);
      
      swap(table, first, down);
      count[1]++;
      
      return down;
   }
   
   //heap sort
   public static <T extends Comparable<T>> void heapSort(T[] table)
   {
      int[] count = new int[2];
      
      buildHeap(table, count);
      shrinkHeap(table, count);
      
      System.out.print("HeapSort ");
      System.out.println(Arrays.toString(table));
      System.out.println("Comparisons: " + count[0]);
      System.out.println("Exchanges: " + count[1]);
   }
   private  static <T extends Comparable<T>> void buildHeap(T[] table, int[] count)
   {
      int n = 1;
      
      while(n < table.length)
      {
         n++;
         int child = n - 1;
         int parent = (child - 1)/2;
         while(parent >=0 && table[parent].compareTo(table[child]) < 0)
         {
            swap(table, parent, child);
            child = parent;
            parent = (child - 1)/2;
            
            count[0]++;
            count[1]++;
         }
         count[0]++;
      }
   }
   private  static <T extends Comparable<T>> void shrinkHeap(T[] table, int[] count)
   {
     int n = table.length;
     
     while(n > 0)
     {
      n--;
      swap(table, 0, n);
      int parent = 0;
      while(true)
      {
         int leftChild = 2 * parent + 1;
         if(leftChild >= n)
         {
            break;
         }
         int rightChild = leftChild + 1;
         
         int maxChild = leftChild;
         
         if(rightChild < n && table[leftChild].compareTo(table[rightChild]) < 0)
         {
            maxChild = rightChild;
         }
         
         if(table[parent].compareTo(table[maxChild]) < 0)
         {
            swap(table, parent, maxChild);
            
            parent = maxChild;
            
            count[1]++;
         }
         else
         {
            break;
         }
         count[0] += 2;
      }
     }  
   }
   private  static <T extends Comparable<T>> void swap(T[] table, int i, int j)
   {
      T temp = table[i];
      table[i] = table[j];
      table[j] = temp;
   }
   
   //merge sort
   public static <T extends Comparable<T>> void mergeSort(T[] table)
   {
      int[] count = new int[2];
      mergeSort(table, 0, table.length - 1, count);
      
      System.out.print("Merge sort: ");
      System.out.println(Arrays.toString(table));
      System.out.println("Number of comparisons " + count[0]);
      System.out.println("Number of exchanges " + count[1]);
   }  

   private static <T extends Comparable<T>> void mergeSort(T[] table, int left, int right, int[] count)
   {
      if(left < right)
      {
         int mid = (left + right) / 2;

         mergeSort(table, left, mid, count);
         mergeSort(table, mid + 1, right, count);

         merge(table, left, mid, right, count);
      }
   }
   private static <T extends Comparable<T>> void merge(T[] table, int left, int mid, int right, int[] count)
   {
      int n1 = mid - left + 1;
      int n2 = right - mid;

      T[] L = Arrays.copyOfRange(table, left, mid + 1);
      T[] R = Arrays.copyOfRange(table, mid + 1, right + 1);

      int i = 0, j = 0, k = left;

      while (i < n1 && j < n2)
      {
         count[0]++;

        if (L[i].compareTo(R[j]) <= 0)
        {
            table[k++] = L[i++];
        }
        else
        {
            table[k++] = R[j++];
        }
      }

      while (i < n1){
        table[k++] = L[i++];
      }
      while (j < n2){
        table[k++] = R[j++];
      }  

      count[1] += n1 + n2;
   }
   
   
   //Shell Sort
   public static<T extends Comparable<T>> void shellSort(T[] table)
   {
      int[] count = new int[2];
      int gap = table.length/2;
      while(gap > 0)
      {
         for(int nextPos = gap; nextPos < table.length; nextPos++)
         {
            insert(table, nextPos, gap, count);
         }
         if(gap == 2)
         {
            gap = 1;
         }
         else
         {
            gap = (int) (gap / 2.2);
         }
      }
      System.out.print("Shell sort: ");
      System.out.println(Arrays.toString(table));
      System.out.println("Number of comparisons " + count[0]);
      System.out.println("Number of exchanges " + count[1]);
      
   }
   private static <T extends Comparable<T>> void insert(T[] table, int nextPos, int gap, int[] count)
   {
      T nextVal = table[nextPos];
      while((nextPos > gap - 1)&& (nextVal.compareTo(table[nextPos - gap]) < 0))
      {
         table[nextPos] = table[nextPos - gap];
         nextPos -= gap;
         count[0]++;
         count[1]++;
      }
      table[nextPos] = nextVal;
      count[0]++;
      count[1]++;
   }
   
   //Insertion Sort
   public static <T extends Comparable<T>> void insertionSort(T[] table)
   {
      int[] count = new int[2];
      for(int nextPos = 1; nextPos < table.length; nextPos++)
      {
         insert(table, nextPos, count);
      }
      System.out.print("Insertion sort: ");
      System.out.println(Arrays.toString(table));
      System.out.println("Number of comparisons " + count[0]);
      System.out.println("Number of exchanges " + count[1]);
   }
   private static <T extends Comparable<T>> void insert(T[] table, int nextPos, int[] count)
   {
      T nextVal = table[nextPos];
      while(nextPos > 0 && nextVal.compareTo(table[nextPos - 1]) < 0)
      {
         table[nextPos] = table[nextPos - 1];
         nextPos--;
         count[0]++;
         count[1]++;
      }
      
      table[nextPos] = nextVal;
      count[0]++;
      count[1]++;
   }
   
   //Bubble Sort
   public static <T extends Comparable<T>> void bubbleSort(T[] table)
   {
      int pass = 1;
      boolean exchanges = false;
      int  comparison = 0, exchange = 0;
      do
      {
         exchanges = false;
         for(int i = 0; i < table.length - pass; i++)
         {
         comparison++;
            if(table[i].compareTo(table[i + 1]) > 0)
            {
               T temp = table[i];
               table[i] = table[i + 1];
               table[i + 1] = temp;
               exchanges = true;   
               exchange++;         
            }
         }
         pass++;
      }
      while(exchanges);
      
      System.out.print("Bubble sort: ");
      System.out.println(Arrays.toString(table));
      System.out.println("Number of comparisons " + comparison);
      System.out.println("Number of exchanges " + exchange);
      
   }
   
   //selection sort
   public static <T extends Comparable<T>> void selectionSort(T[] table)
   {
      int n = table.length;
      int comparisons = 0, exchanges = 0;
      
      for(int fill = 0; fill < n - 1; fill++)
      {
         int posMin = fill;
         
         for(int next = fill + 1; next < n; next++)
         {
            comparisons++;

            if(table[next].compareTo(table[posMin]) < 0)
            {
               posMin = next;
            }
         }

         if(posMin != fill)
         {
            T temp = table[fill];
            table[fill] = table[posMin];
            table[posMin] = temp;
            exchanges++;
         }
      }
      
      
      System.out.print("Selection sort: ");
      System.out.println(Arrays.toString(table));
      System.out.println("Number of comparisons " + comparisons);
      System.out.println("Number of exchanges " + exchanges);
   }
}