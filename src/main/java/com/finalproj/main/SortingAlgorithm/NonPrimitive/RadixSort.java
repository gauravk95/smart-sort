package com.finalproj.main.SortingAlgorithm.NonPrimitive;

import java.util.ArrayList;
import java.util.Arrays;

import com.finalproj.main.GlobalValuesAndConstants;
import com.finalproj.main.DataModels.RecordRow;

public class RadixSort {

	// A utility function to get maximum value in arr[]
    static int getMax(ArrayList<RecordRow> arr, int n)
    {
        int mx = arr.get(0).getCompInteger();
        for (int i = 1; i < n; i++)
            if (arr.get(i).getCompInteger() > mx)
                mx = arr.get(i).getCompInteger();
        return mx;
    }
 
    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(ArrayList<RecordRow> arr, int n, int exp)
    {
        RecordRow output[] = new RecordRow[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);
 
        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (arr.get(i).getCompInteger()/exp)%10 ]++;
 
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        if(GlobalValuesAndConstants.SORT_DIRECTION==1)
        {
        	//ascending order
	        for (i = 1; i < 10; i++)
	            count[i] += count[i - 1];
        }
        else
        {
        	//descending order
	        for (i = 8; i >=0 ; i--)
	            count[i] += count[i+1];
        }
        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (arr.get(i).getCompInteger()/exp)%10 ] - 1] = arr.get(i);
            count[ (arr.get(i).getCompInteger()/exp)%10 ]--;
        }
 
        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            arr.set(i,output[i]);
    }
 
    // The main function to that sorts arr[] of size n using
    // Radix Sort
    public static void sort(ArrayList<RecordRow> arr)
    {
        // Find the maximum number to know number of digits
        int m = getMax(arr, arr.size());
 
        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, arr.size(), exp);
        
        
        //print the elements
       //System.out.println("\n****************************SORTED RECORD****************************");
       //printRecords(arr);
    }
 
    public static <T> void printRecords(ArrayList<T> row)
    {
    	
    	for(int i=0;i<row.size();i++)
    	{
    		System.out.println(row.get(i).toString());
    	}
    }

    
}
