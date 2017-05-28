package com.finalproj.main.SortingAlgorithm.NonPrimitive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HeapSort<T>{
	
	private ArrayList<T> row; 
	 private Comparator<T> c;
	 	private int N;  

    
    /*************************STRING************************/
    /* Sort Function */
    public void sort(ArrayList<T> row,Comparator<? super T> c ) {
        this.row = row;
        this.c =(Comparator<T>) c;
        this.N= row.size();
        
        heapify();        
        for (int i = N; i > 0; i--)
        {
            Collections.swap(row,0, i);
            N = N-1;
            maxheap(0);
        }
        
        
       //System.out.println("\n****************************SORTED RECORD****************************");
      // printRecords(row);
        
    }     
    /* Function to build a heap */   
    public void heapify()
    {
        N = row.size()-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(i);        
    }
    /* Function to swap largest element in heap */        
    public void maxheap(int i)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && (c.compare(row.get(left),row.get(i))>0))
            max = left;
        if (right <= N && (c.compare(row.get(right),row.get(max))>0))        
            max = right;
 
        if (max != i)
        {
            Collections.swap(row, i, max);
            maxheap(max);
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
