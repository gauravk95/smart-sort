package com.finalproj.main.SortingAlgorithm.NonPrimitive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QuickSort<T>{

     private ArrayList<T> row; 
 	private Comparator<T> c;
 	private int n;     
    
     public void sort(ArrayList<T> row,Comparator<? super T> c ) {
             this.row = row;
             this.c =(Comparator<T>) c;
             this.n = row.size();
             quickSort(0, n - 1);
             
            //System.out.println("\n****************************SORTED RECORD****************************");
            //printRecords(row);
             
     }

     private void quickSort(int low, int high) {
             int i = low, j = high;
             // Get the pivot element from the middle of the list
             T pivot = row.get(low + (high-low)/2);

             // Divide into two lists
             while (i <= j) {
    
                     // If the current value from the left list is smaller than the pivot
                     // element then get the next element from the left list
                     while (c.compare(row.get(i),pivot)<0) {
                             i++;
                     }
                     // If the current value from the right list is larger than the pivot
                     // element then get the next element from the right list
                     while (c.compare(row.get(j),pivot)>0) {
                             j--;
                     }

                     // If we have found a value in the left list which is larger than
                     // the pivot element and if we have found a value in the right list
                     // which is smaller than the pivot element then we exchange the
                     // values.
                     // As we are done we can increase i and j
                     if (i <= j) {

                    	 	Collections.swap(row, i, j);
                             i++;
                             j--;
                     }
             }
             
           
             // Recursion
             if (low < j)
                     quickSort(low, j);
             if (i < high)
                     quickSort(i, high);
     }
     
     
public static <T> void printRecords(ArrayList<T> row)
{
	
	for(int i=0;i<row.size();i++)
	{
		System.out.println(row.get(i).toString());
	}
}     


}
