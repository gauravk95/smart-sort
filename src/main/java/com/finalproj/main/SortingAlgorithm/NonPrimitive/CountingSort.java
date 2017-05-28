package com.finalproj.main.SortingAlgorithm.NonPrimitive;

import java.util.ArrayList;
import java.util.Arrays;

import com.finalproj.main.GlobalValuesAndConstants;
import com.finalproj.main.DataModels.RecordRow;


public class CountingSort {
	
	 private static final int MAX_RANGE = 1000000;
	   
	 /** Counting Sort function **/
	    public static ArrayList<RecordRow> sort(ArrayList<RecordRow> arr )
	    {
	    	RecordRow[] tempArr = new RecordRow[arr.size()];
	    	ArrayList newarr = new ArrayList(arr.size());
	        int N = arr.size();
	     
	        /** find max and min values **/
	        Integer max = arr.get(0).getCompInteger(), min = arr.get(0).getCompInteger();
	        for (int i = 1; i < N; i++)
	        {
	            if (arr.get(i).getCompInteger() > max)
	                max =arr.get(i).getCompInteger();
	            if (arr.get(i).getCompInteger() < min)
	                min = arr.get(i).getCompInteger();
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
	            count[arr.get(i).getCompInteger() - min]++;
	       
	        
	        
	        if(GlobalValuesAndConstants.SORT_DIRECTION==1)
	        { 
		        /** modify count so that positions in final array is obtained **/
		        for (int i = 1; i < range; i++)
		            count[i] += count[i - 1];
	        }
	        else
	        {  /** modify count so that positions in final array is obtained **/
		        for (int i = range-2; i>=0; i--)
		            count[i] += count[i+1];	
	        }
	        /** modify original array **/
	        int j = 0;
	        for (int i = 0; i < arr.size(); i++)
	        {
	        	tempArr[count[arr.get(i).getCompInteger()-min]-1]=arr.get(i);
	        	count[arr.get(i).getCompInteger()-min]++;
	        }
	        
	        
	        newarr.addAll(Arrays.asList(tempArr));
	        
	        //System.out.println("\n****************************SORTED RECORD****************************");
	        //printRecords(newarr);
	        
	        return newarr;
	    
	    } 
	 
	    /*
	    /** Counting Sort function 
	     * @param <T>
	    public static <K,V> void sort( ArrayList<CounterKeyValueInterface> row)
	    {
	        int N = row.size();
	        boolean isOutOfRange = false;
	        
	        Multimap<K,V> multiMap = ArrayListMultimap.create();
	        
	        for(int i =0;i<row.size();i++)
	        {
	        	multiMap.put((K)row.get(i).getKey(),(V)row.get(i).getValue());
	        	
	        	//check if the number of iteration are more than Max_range
	        	if(i>MAX_RANGE)
	        	{
	        		//check if the number of keys is more than max range
	        		if(multiMap.keys().size()>MAX_RANGE)
	        		{	
	        			isOutOfRange = true;
	        			break;
	        		
	        		}
	        	}
	        }
	 
	        /** check if range is small enough for count array 
	        /** else it might give out of memory exception while allocating memory for array 
	        if (isOutOfRange)
	        {
	            System.out.println("\nError : Range too large for sort");
	    
	        }
	        else
	        {
	        	
	        	row.clear();
	        	
	        	Set<K> keys= multiMap.keySet();
	 
	        	// iterate through the key set and display key and values
	            for (K key : keys) {
	                System.out.println("Key = " + key);
	                
	                for(V val: multiMap.get(key) )
	                {
	                	row.add((CounterKeyValueInterface) val);
	                }
	            }
		        
	        }
	        
            
           System.out.println("\n****************************SORTED RECORD****************************");
           printRecords(row);
	        
	    } 
*/	    
	    public static <T> void printRecords(ArrayList<T> row)
	    {
	    	
	    	for(int i=0;i<row.size();i++)
	    	{
	    		System.out.println(row.get(i).toString());
	    	}
	    }

	    
}
