package com.finalproj.main.SortingAlgorithm.NonPrimitive;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort<T>{

	private ArrayList<T> row; 
	private Comparator<T> c;
	private int n;
	private ArrayList<T> helperRow;
  

  public void sort(ArrayList<T> row,Comparator<? super T> c ) {
      this.row = row;
      this.c = (Comparator<T>) c;
      this.n = row.size();
      this.helperRow = new ArrayList<T>();
      mergeSort(0, n - 1);
      
     //System.out.println("\n****************************SORTED RECORD****************************");
     //printRecords(row);
      
}

private void mergeSort(int low, int high) {
      // check if low is smaller than high, if not then the array is sorted
      if (low < high) {
              // Get the index of the element which is in the middle
              int middle = low + (high - low) / 2;
              // Sort the left side of the array
              mergeSort(low, middle);
              // Sort the right side of the array
              mergeSort(middle + 1, high);
              // Combine them both
              mergeRecords(low, middle, high);
      }
     
}

private void mergeRecords(int low, int middle, int high) {

		helperRow.clear();
	
      // Copy both parts into the helpStr array
      for (int i = low; i <= high; i++) {
              helperRow.add(row.get(i));
      }

      int i = low;
      int j = middle + 1;
      int k = low;
      // Copy the smallest values from either the left or the right side back
      // to the original array
      while (i <= middle && j <= high) {
              if (c.compare(helperRow.get(i-low),helperRow.get(j-low))<=0 ) {
                   row.set(k, helperRow.get(i-low));
                      i++;
              } else {
            	  row.set(k, helperRow.get(j-low));
                      j++;
              }
              k++;
      }
      // Copy the rest of the left side of the array into the target array
      while (i <= middle) {
    	  row.set(k, helperRow.get(i-low));
              k++;
              i++;
      }
      

}


public static <T> void printRecords(ArrayList<T> row)
{

for(int i=0;i<row.size();i++)
{
	System.out.println(row.get(i).toString());
}
}     

}
