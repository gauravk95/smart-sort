package com.finalproj.main.SortingAlgorithm.Primitive;

public class QuickSortPrimitive {
	
	 private Integer[] numbers;
     private int number;
     
     private Float[] numFloat;
     
     private String[] numString;
     
     /**************************INTEGERS****************************/
     public Integer[] sort(Integer[] values) {
             this.numbers = values;
             number = values.length;
             quicksort(0, number - 1);
             
             return numbers;
     }

     private void quicksort(int low, int high) {
             int i = low, j = high;
             // Get the pivot element from the middle of the list
             int pivot = numbers[low + (high-low)/2];

             // Divide into two lists
             while (i <= j) {
                     // If the current value from the left list is smaller than the pivot
                     // element then get the next element from the left list
                     while (numbers[i] < pivot) {
                             i++;
                     }
                     // If the current value from the right list is larger than the pivot
                     // element then get the next element from the right list
                     while (numbers[j] > pivot) {
                             j--;
                     }

                     // If we have found a value in the left list which is larger than
                     // the pivot element and if we have found a value in the right list
                     // which is smaller than the pivot element then we exchange the
                     // values.
                     // As we are done we can increase i and j
                     if (i <= j) {
                             exchange(i, j);
                             i++;
                             j--;
                     }
             }
             // Recursion
             if (low < j)
                     quicksort(low, j);
             if (i < high)
                     quicksort(i, high);
     }

     private void exchange(int i, int j) {
             Integer temp = numbers[i];
             numbers[i] = numbers[j];
             numbers[j] = temp;
     }
     
     /**************************FLOAT****************************/
     public Float[] sort(Float[] values) {
             this.numFloat = values;
             number = values.length;
             quicksortfloat(0, number - 1);
             
             return numFloat;
     }

     private void quicksortfloat(int low, int high) {
             int i = low, j = high;
             // Get the pivot element from the middle of the list
             Float pivot = numFloat[low + (high-low)/2];

             // Divide into two lists
             while (i <= j) {
                     // If the current value from the left list is smaller than the pivot
                     // element then get the next element from the left list
                     while (numFloat[i] < pivot) {
                             i++;
                     }
                     // If the current value from the right list is larger than the pivot
                     // element then get the next element from the right list
                     while (numFloat[j] > pivot) {
                             j--;
                     }

                     // If we have found a value in the left list which is larger than
                     // the pivot element and if we have found a value in the right list
                     // which is smaller than the pivot element then we exchange the
                     // values.
                     // As we are done we can increase i and j
                     if (i <= j) {
                             exchangefloat(i, j);
                             i++;
                             j--;
                     }
             }
             // Recursion
             if (low < j)
                     quicksortfloat(low, j);
             if (i < high)
                     quicksortfloat(i, high);
     }

     private void exchangefloat(int i, int j) {
             Float temp = numFloat[i];
             numFloat[i] = numFloat[j];
             numFloat[j] = temp;
     }
     
     /**************************STRING****************************/
     public String[] sort(String[] values) {
             this.numString = values;
             number = values.length;
             quicksortstring(0, number - 1);
             
             return numString;
     }

     private void quicksortstring(int low, int high) {
             int i = low, j = high;
             // Get the pivot element from the middle of the list
             String pivot = numString[low + (high-low)/2];

             // Divide into two lists
             while (i <= j) {
    
                     // If the current value from the left list is smaller than the pivot
                     // element then get the next element from the left list
                     while (numString[i].compareTo(pivot)<0) {
                             i++;
                     }
                     // If the current value from the right list is larger than the pivot
                     // element then get the next element from the right list
                     while (numString[j].compareTo(pivot)>0) {
                             j--;
                     }

                     // If we have found a value in the left list which is larger than
                     // the pivot element and if we have found a value in the right list
                     // which is smaller than the pivot element then we exchange the
                     // values.
                     // As we are done we can increase i and j
                     if (i <= j) {
                             exchangestring(i, j);
                             i++;
                             j--;
                     }
             }
             
           
             // Recursion
             if (low < j)
                     quicksortstring(low, j);
             if (i < high)
                     quicksortstring(i, high);
     }

     private void exchangestring(int i, int j) {
             String temp = numString[i];
             numString[i] = numString[j];
             numString[j] = temp;
     }

}
