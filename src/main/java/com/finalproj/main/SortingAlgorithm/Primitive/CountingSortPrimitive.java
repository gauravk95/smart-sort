package com.finalproj.main.SortingAlgorithm.Primitive;

public class CountingSortPrimitive {
	
	 private static final int MAX_RANGE = 1000000;
	   
	 /** Counting Sort function **/
	    public Integer[] sort( Integer[] arr )
	    {
	        int N = arr.length;
	     
	        /** find max and min values **/
	        Integer max = arr[0], min = arr[0];
	        for (int i = 1; i < N; i++)
	        {
	            if (arr[i] > max)
	                max = arr[i];
	            if (arr[i] < min)
	                min = arr[i];
	        }
	        int range = max - min + 1;
	 
	        /** check if range is small enough for count array **/
	        /** else it might give out of memory exception while allocating memory for array **/
	        if (range > MAX_RANGE)
	        {
	            System.out.println("\nError : Range too large for sort");
	           return null;
	        }
	 
	        int[] count = new int[range];
	        /** make count/frequency array for each element **/
	        for (int i = 0; i < N; i++)
	            count[arr[i] - min]++;
	        /** modify count so that positions in final array is obtained **/
	        for (int i = 1; i < range; i++)
	            count[i] += count[i - 1];
	        /** modify original array **/
	        int j = 0;
	        for (int i = 0; i < range; i++)
	            while (j < count[i])
	                arr[j++] = i + min;
	        
	        return arr;
	    }    

}
