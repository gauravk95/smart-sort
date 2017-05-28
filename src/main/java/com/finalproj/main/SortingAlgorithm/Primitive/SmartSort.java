package com.finalproj.main.SortingAlgorithm.Primitive;

/****************************************************

 * Smart Sort is an Hybrid, Adaptive and efficient sorting
 * algorithm that tries to utilize the best of sorting algorithm 
 * like Insertion Sort, Shell Sort, Quick Sort, Counting Sort and
 * Merge Sort
 * 
 * 
 * @author Gaurav Kumar
 *
 *****************************************************/


///TODO: Things to take care of before finalizing the algorithm
/*
 * Improve the main analyser itself
 * 
 * Improve the input analyser for float and string datatypes
 * 
 */


public class SmartSort {
	
	public static final int DATA_TYPE_INTEGER=1;
	public static final int DATA_TYPE_FLOAT=2;
	public static final int DATA_TYPE_STRING=3;
	
	
	private int dataType ;
	
	
	private int IS_MIN_SIZE = 0;
	private int IS_DEFAULT_SIZE = 100;
	private int IS_MAX_SIZE = 150;
	
	private int SS_MIN_SIZE = IS_MAX_SIZE;
	private int SS_MAX_SIZE = 100000;
	private int SS_MAX_RANGE = 50000;
	
	private int QS_MIN_SIZE = IS_MAX_SIZE;
	private int QS_MAX_SIZE = 500000;
	private int QS_DEFAULT_SIZE = 50000;
	
	private int CS_MIN_SIZE = IS_MAX_SIZE;
	private int CS_MAX_SIZE = Integer.MAX_VALUE;
	private int CS_MAX_RANGE = 100000;
	
	private int CHUNK_MIN_SIZE = IS_MAX_SIZE;
	private int CHUNK_MAX_SIZE = Integer.MAX_VALUE;
	
	private int CUR_CHUNK_SIZE = 10000;
	
	
	/*
	///test values
	private int IS_MIN_SIZE = 0;
	private int IS_MAX_SIZE = 50;
	private int IS_DEFAULT_SIZE = 30;
	
	private int SS_MIN_SIZE = IS_MAX_SIZE;
	private int SS_MAX_SIZE = 100;
	private int SS_MAX_RANGE = 100;
	
	private int QS_MIN_SIZE = 13;
	private int QS_MAX_SIZE = 500;
	private int QS_DEFAULT_SIZE = 200;
	
	private int CS_MIN_SIZE = IS_MAX_SIZE;
	private int CS_MAX_SIZE = 500;
	private int CS_MAX_RANGE = 0;
	
	private int CHUNK_MIN_SIZE = IS_MAX_SIZE;
	private int CHUNK_MAX_SIZE = 500;
	
	private int CUR_CHUNK_SIZE = 60;
	*/
	private Integer[] arrInteger;
	
	private Float[] arrFloat;
	
	private String[] arrString;
	
	//k is the distribution factor, k=1(0% repetition), k=2(100% repetition) ....
	private float k = 1.2f;
	
	//size of the input array
	private int N;
	
	//used to define each chunk/block properties
	private int first,last;
	private int minInteger,maxInteger; 
	
	//used by merger
	//defines stack data structure
	private Integer[] stackInteger;
	private String[] stackString;
	private Float[] stackFloat;
	
	//pointer for the stack
	private int top=-1,low=0;
	
	public SmartSort(Integer [] arr)
	{
		this.arrInteger = arr;
		this.N = arr.length;
		this.dataType = DATA_TYPE_INTEGER;
		//initialize variables for merger
		this.stackInteger = new Integer[N];
	}
	public SmartSort(Float [] arr)
	{
		this.arrFloat = arr;
		this.N = arr.length;
		this.dataType = DATA_TYPE_FLOAT;
		//initialize variables for merger
		this.stackFloat = new Float[N];
	}
	public SmartSort(String [] arr)
	{
		this.arrString = arr;
		this.N = arr.length;
		this.dataType = DATA_TYPE_STRING;
		//initialize variables for merger
		this.stackString = new String[N];
	}
	
	private void initSort()
	{
		top=-1;
		low = 0;
	}
	
	public void sort()
	{
		//Initializes the variables required for the sort
		initSort();
		
		//System.out.println("***************************************************************************************");
		
		//input analyzer
		if(dataType==DATA_TYPE_INTEGER)
		{
			inputScannerAndAnalyserInteger();
		}
		else if(dataType==DATA_TYPE_FLOAT)
		{
			inputScannerAndAnalyserFloat();
		}
		else
		{
			inputScannerAndAnalyserString();
		}
		
		//System.out.println("**************************************************************************************");
	}


	/********************************************************
	 * Scans the input and accordingly assigns it to chunks
	 * 
	 * Uses  Threshold values of different sorting algorithm
	 * and the chunk properties(min,max,size) to determine the 
	 * assignment of appropriate sorting algorithm
	 ******************************************************/
	private void inputScannerAndAnalyserInteger() {
		// TODO create for descending order, too
		
		//ensure all element are covered once
		for(int i = 0; i < N-1 ; i++)
		{
			//Initialize(or)Reset the variables min,max,first,last
			minInteger=arrInteger[i];
			maxInteger=arrInteger[i+1];
			first = i;
			last = i;
			
			//check if the elements are already sorted in ascending order
			while((last<=N-2)&&(arrInteger[last]<=arrInteger[last+1]))
			{
				//update the chunk properties
				//since the while comparison ensures that arr[last+1] is always greater 
					maxInteger=arrInteger[last+1];
					
				//increment the last
					last++;
			}
			
			//if the number of sorted elements is greater than the min chunk size 
			if((last-first)>=CHUNK_MIN_SIZE)
			{
				//printStatus("Chunk sent to merger...","Already Sorted!",first,last);
				//submit the chunk to merger as already sorted
				submitChunkToMergerInteger(first,last);
			}
			else
			{
				//if there are less than IS_MAX_SIZE elements to sort then, use insertion sort
				//and end the loop
				if((N-first)<=IS_MAX_SIZE)
				{
					//printStatus("Chunk assigned to Insertion sort","Last few Elements!",first,N-1);
					
					createInsertionSortJobInteger(first,N-1,true);
					
					//end loop, as all elements are scanned
					break;
				}
				
				//continue scanning through the data, until CS_MAX_RANGE is not exceeded, 
				//an all elements are not scanned
				while(((maxInteger-minInteger)<=CS_MAX_RANGE)&&(last<N-1))
				{
					//change the min and max values
					if(arrInteger[last]>maxInteger)
					{
						maxInteger=arrInteger[last];
					}
					else if(arrInteger[last]<minInteger)
					{
						minInteger=arrInteger[last];
					}
					
					last++;
					
				}
				
				int numEle = last-first;
				//check for the validity, if count sort can be applied
				//i.e if the # of elements for the chunk is CS_MIN_SIZE 
				if((numEle)>=CS_MIN_SIZE)
				{
					//printStatus("Chunk assigned to count sort","Number of chunk elements within range!",first,last);
					createCountSortJobInteger(first,last,minInteger,maxInteger);
				}
				else 
				{
					
					//continue scanning through the data, until SS_MAX_RANGE is not exceeded, 
					//an all elements are not scanned
					while((k*(maxInteger-minInteger)<=SS_MAX_RANGE)&&(last<N-1))
					{
						//change the min and max values
						if(arrInteger[last]>maxInteger)
						{
							maxInteger=arrInteger[last];
						}
						else if(arrInteger[last]<minInteger)
						{
							minInteger=arrInteger[last];
						}
						
						last++;
						
					}
					
						numEle = last-first;
					
						if((SS_MIN_SIZE<=(numEle))&&((numEle)<=SS_MAX_SIZE))
						{
							//printStatus("Chunk assigned to Shell sort","Elements are repeated and cover large range!",first,last);
							//if the repetition is greater than or equal 100% and is within limit
							createShellSortJobInteger(first,last);
						}
						else
						{
							//if the program enters here it means that all other algorithm were skipped
							//if the elements are fairly random, use quick sort
							
							//if the # of elements exceeds the QS_MAX_SIZE, limit the chuck/block to the QS_MAX_SIZE
							if((numEle)>=QS_MAX_SIZE)
							{
								last= first+QS_MAX_SIZE;
								//printStatus("Chunk assigned to Quick Sort","Elements are fairly random! Range Exceeded..",first,last);
								createQuickSortJobInteger(first,first+QS_MAX_SIZE);
								
							}
							else if((numEle)>=QS_MIN_SIZE)
							{
								//printStatus("Chunk assigned to Quick Sort","Elements are fairly random!",first,last);
								//if # of elements within range
								createQuickSortJobInteger(first,last);
							}
							else
							{
								//this means we have scanned till last, and numEle is less than QS_MIN_SIZE
								//assign insertion sort,using default size for IS
								
									last = first+IS_DEFAULT_SIZE;
									//printStatus("Chunk assigned to Insertion sort","Few Elements scanned!",first,last);					
									createInsertionSortJobInteger(first,last,true);
								
							}
						}
					}
			}
			
			//finally change i to last, to skip already scanned data
			i=last;
		}
			
	}
	
	private void inputScannerAndAnalyserFloat() {
		// TODO create for descending order, too
		
		//ensure all element are covered once
		for(int i = 0; i < N-1 ; i++)
		{
			first = i;
			last = i;
			
			//check if the elements are already sorted in ascending order
			while((last<=N-2)&&(arrFloat[last]<=arrFloat[last+1]))
			{
				//update the chunk properties
				//since the while comparison ensures that arr[last+1] is always greater 
				//maxFloat=arrFloat[last+1];
					
				//increment the last
					last++;
			}
			
			//if the number of sorted elements is greater than the min chunk size 
			if((last-first)>=CHUNK_MIN_SIZE)
			{
				//printStatus("Chunk sent to merger...","Already Sorted!",first,last);
				//submit the chunk to merger as already sorted
				submitChunkToMergerFloat(first,last);
			}
			else
			{
				if((N-first)>=QS_DEFAULT_SIZE)
				{
					last = first+QS_DEFAULT_SIZE;
					//printStatus("Chunk assigned to Quick Sort","Elements are fairly random!",first,last);
					//if # of elements within range
					createQuickSortJobFloat(first,last);
				}
				else if((N-first)>=QS_MIN_SIZE)
				{
					last = first+QS_MIN_SIZE;
					//printStatus("Chunk assigned to Quick Sort","Elements are fairly random!",first,last);
					//if # of elements within range
					createQuickSortJobFloat(first,last);
				}
				else
				{
					//this means we have scanned till last, and numEle is less than QS_MIN_SIZE
					//assign insertion sort,using default size for IS
					last=N-1;
					//printStatus("Chunk assigned to Insertion sort","Few Elements scanned!",first,last);					
					createInsertionSortJobFloat(first,last,true);
					
				}
			}
			
			//finally change i to last, to skip already scanned data
			i=last;
		}
	}
	
	private void inputScannerAndAnalyserString() {
		// TODO create for descending order, too
		
		//ensure all element are covered once
		for(int i = 0; i < N-1 ; i++)
		{
			first = i;
			last = i;
			
			//check if the elements are already sorted in ascending order
			while((last<=N-2)&&(arrString[last].compareTo(arrString[last+1])<=0))
			{
				//update the chunk properties
				//since the while comparison ensures that arr[last+1] is always greater 
				//maxString=arrString[last+1];
					
				//increment the last
					last++;
			}
			
			//if the number of sorted elements is greater than the min chunk size 
			if((last-first)>=CHUNK_MIN_SIZE)
			{
				//printStatus("Chunk sent to merger...","Already Sorted!",first,last);
				//submit the chunk to merger as already sorted
				submitChunkToMergerString(first,last);
			}
			else
			{
				if((N-first)>=QS_DEFAULT_SIZE)
				{
					last = first+QS_DEFAULT_SIZE;
					//printStatus("Chunk assigned to Quick Sort","Elements are fairly random!",first,last);
					//if # of elements within range
					createQuickSortJobString(first,last);
				}
				else if((N-first)>=QS_MIN_SIZE)
				{
					last = first+QS_MIN_SIZE;
					//printStatus("Chunk assigned to Quick Sort","Elements are fairly random!",first,last);
					//if # of elements within range
					createQuickSortJobString(first,last);
				}
				else
				{
					//this means we have scanned till last, and numEle is less than QS_MIN_SIZE
					//assign insertion sort,using default size for IS
					last = N-1;
					//printStatus("Chunk assigned to Insertion sort","Few Elements scanned!",first,last);					
					createInsertionSortJobString(first,last,true);
					
				}
			}
			
			//finally change i to last, to skip already scanned data
			i=last;
		}
	}
	
	private void printStatus(String info,String stat,int first,int last)
	{
		System.out.println("INFO: "+info+"\tStatus:"+stat+"\tarr["+first+", "+last+"]");
	}
	
	/********************************************************
	 * This method performs insertion sort on the assigned chunk
	 * 
	 * Insertion sort works fast for small size input(like 150) 
	 * and takes advantage of repetition and partially sorted 
	 * conditions
	 ********************************************************/
	public void createInsertionSortJobInteger(int startIndex, int endIndex, boolean b) {
		// TODO Auto-generated method stub

		for (int j = startIndex+1; j <= endIndex; j++) { 
            int key = arrInteger[j];  
            int i = j-1;  
            while ( (i >= startIndex) && ( arrInteger [i] > key ) ) { 
                arrInteger [i+1] = arrInteger [i];  
                i--;  
            }  
            arrInteger[i+1] = key;  
        }  
		
		//once insertion sort is completed, submit the chunk to merger as its sorted
		//only submit to merger if b is true i.e insertion is not a part of Quick Sort
		if(b)
		submitChunkToMergerInteger(startIndex,endIndex);
	}
	
	public void createInsertionSortJobFloat(int startIndex, int endIndex, boolean b) {
		// TODO Auto-generated method stub

		for (int j = startIndex+1; j <= endIndex; j++) { 
            Float key = arrFloat[j];  
            int i = j-1;  
            while ( (i >= startIndex) && ( arrFloat [i] > key ) ) { 
                arrFloat [i+1] = arrFloat [i];  
                i--;  
            }  
            arrFloat[i+1] = key;  
        }  
		
		//once insertion sort is completed, submit the chunk to merger as its sorted
		//only submit to merger if b is true i.e insertion is not a part of Quick Sort
		if(b)
	     submitChunkToMergerFloat(startIndex,endIndex);
	}

	public void createInsertionSortJobString(int startIndex, int endIndex, boolean b) {
		// TODO Auto-generated method stub
		for (int j = startIndex+1; j <= endIndex; j++) { 
            String key = arrString[j];  
            int i = j-1;  
            while ( (i >= startIndex) && ( arrString [i].compareTo(key)>0 ) ) { 
                arrString [i+1] = arrString [i];  
                i--;  
            }  
            arrString[i+1] = key;  
        }  
		
		//once insertion sort is completed, submit the chunk to merger as its sorted
		//only submit merger if b is true i.e insertion is not a part of Quick Sort
		if(b)
		submitChunkToMergerString(startIndex,endIndex);
	}


	/*************************************************************************
	 * This method performs quick sort on the assigned chunk
	 * 
	 * Quick sort is usually very fast, but its nature is unpredictable
	 * 
	 * To increase the efficiency of the program, the following implementation 
	 * of the quick sort internally uses (Shell Sort  and Insertion Sort), 
	 * if the conditions are satisfied
	 * 
	 * Reason to use Insertion sort/ Shell sort for internal sorting
	 * 
	 * Insertion sort works fast on pre-sorted/partially sorted 
	 * array with less input size like around 150
	 * 
	 * Shell sort works fast for medium size input(like 100000) and takes
	 * advantage of repetition and partially sorted conditions
	 **************************************************************************/
	public void createQuickSortJobInteger(int startIndex, int endIndex) {
		quickSortInteger(startIndex,endIndex);
		
		//once quick sort is completed, submit the chunk to merger as its sorted
		submitChunkToMergerInteger(startIndex,endIndex);
	}
	
	public void createQuickSortJobFloat(int startIndex, int endIndex) {
		quickSortFloat(startIndex,endIndex);
		
		//once quick sort is completed, submit the chunk to merger as its sorted
		submitChunkToMergerFloat(startIndex,endIndex);
	}
	
	
	public void createQuickSortJobString(int startIndex, int endIndex) {
		quickSortString(startIndex,endIndex);
		
		//once quick sort is completed, submit the chunk to merger as its sorted
		 submitChunkToMergerString(startIndex,endIndex);
	}
	
	
	/***********************************************************
	 * Performs the quick sort
	 ***********************************************************/
	 private void quickSortInteger(int low, int high) {
		 
	        int i = low, j = high;
	        // Get the pivot element from the middle of the list
	        int pivot = arrInteger[low + (high-low)/2];
	
	        // Divide into two lists
	        while (i <= j) {
	                // If the current value from the left list is smaller than the pivot
	                // element then get the next element from the left list
	                while (arrInteger[i] < pivot) {
	                        i++;
	                }
	                // If the current value from the right list is larger than the pivot
	                // element then get the next element from the right list
	                while (arrInteger[j] > pivot) {
	                        j--;
	                }
	
	                // If we have found a value in the left list which is larger than
	                // the pivot element and if we have found a value in the right list
	                // which is smaller than the pivot element then we exchange the
	                // values.
	                // As we are done we can increase i and j
	                if (i <= j) {
	                        exchangeInteger(i, j);
	                        i++;
	                        j--;
	                }
	        }
	        // Recursive call to left and right sub-array
	        //if the sub array size is lower than the QS_MIN_SIZE, use Insertion sort 
	        //as it works faster for smaller arrays
	        if ((low < j) && ((j-low)>=QS_MIN_SIZE))
	                quickSortInteger(low, j);
	        else
	        		createInsertionSortJobInteger(low,j,false);
	        
	        if ((i < high)&& ((high-i)>=QS_MIN_SIZE))
	                quickSortInteger(i, high);
	        else
	        		createInsertionSortJobInteger(i,high,false);
	}
	 
 private void quickSortFloat(int low, int high) {
	 
	        int i = low, j = high;
	        // Get the pivot element from the middle of the list
	       Float pivot = arrFloat[low + (high-low)/2];
	
	        // Divide into two lists
	        while (i <= j) {
	                // If the current value from the left list is smaller than the pivot
	                // element then get the next element from the left list
	                while (arrFloat[i] < pivot) {
	                        i++;
	                }
	                // If the current value from the right list is larger than the pivot
	                // element then get the next element from the right list
	                while (arrFloat[j] > pivot) {
	                        j--;
	                }
	
	                // If we have found a value in the left list which is larger than
	                // the pivot element and if we have found a value in the right list
	                // which is smaller than the pivot element then we exchange the
	                // values.
	                // As we are done we can increase i and j
	                if (i <= j) {
	                        exchangeFloat(i, j);
	                        i++;
	                        j--;
	                }
	        }
	        // Recursive call to left and right sub-array
	        //if the sub array size is lower than the QS_MIN_SIZE, use Insertion sort 
	        //as it works faster for smaller arrays
	        if ((low < j) && ((j-low)>=QS_MIN_SIZE))
	                quickSortFloat(low, j);
	        else
	        		createInsertionSortJobFloat(low,j,false);
	        
	        if ((i < high)&& ((high-i)>=QS_MIN_SIZE))
	                quickSortFloat(i, high);
	        else
	        		createInsertionSortJobFloat(i,high,false);
	}
 
 private void quickSortString(int low, int high) {

        int i = low, j = high;
        // Get the pivot element from the middle of the list
        String pivot = arrString[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
                // If the current value from the left list is smaller than the pivot
                // element then get the next element from the left list
                while (arrString[i].compareTo(pivot) < 0) {
                        i++;
                }
                // If the current value from the right list is larger than the pivot
                // element then get the next element from the right list
                while (arrString[j].compareTo(pivot) > 0 ) {
                        j--;
                }

                // If we have found a value in the left list which is larger than
                // the pivot element and if we have found a value in the right list
                // which is smaller than the pivot element then we exchange the
                // values.
                // As we are done we can increase i and j
                if (i <= j) {
                        exchangeString(i, j);
                        i++;
                        j--;
                }
        }
        
        // Recursive call to left and right sub-array
        //if the sub array size is lower than the QS_MIN_SIZE, use Insertion sort 
        //as it works faster for smaller arrays
        if ((low < j) && ((j-low)>=QS_MIN_SIZE))
                quickSortString(low, j);
        else
        		createInsertionSortJobString(low,j,false);
        
        if ((i < high)&& ((high-i)>=QS_MIN_SIZE))
                quickSortString(i, high);
        else
        		createInsertionSortJobString(i,high,false);
}
	
	 /****************************************************************
	  * Utility function to support exchange of 2 number 
	  * based on index i,j
	  ***************************************************************/
	private void exchangeInteger(int i, int j) {
	        Integer temp = arrInteger[i];
	        arrInteger[i] = arrInteger[j];
	        arrInteger[j] = temp;
	}
	
	private void exchangeFloat(int i, int j) {
        Float temp = arrFloat[i];
        arrFloat[i] = arrFloat[j];
        arrFloat[j] = temp;
}

	private void exchangeString(int i, int j) {
        String temp = arrString[i];
        arrString[i] = arrString[j];
        arrString[j] = temp;
}


	/********************************************************
	 * This method performs shell sort on the assigned chunk
	 * 
	 * Shell sort works fast for medium size input(like 100000) 
	 * and takes advantage of repetition and partially sorted 
	 * conditions
	 ********************************************************/
	public void createShellSortJobInteger(int startIndex, int endIndex) {

		//plus 1 is required to include element at end index in sorting process
		int size = endIndex-startIndex+1;
		
		 int increment = size / 2;
	        while (increment > 0) 
	        {
	            for (int i = increment; i < size; i++) 
	            {
	                int j = i;
	                int temp = arrInteger[startIndex+i];
	                while (j >= increment && arrInteger[startIndex+j - increment] > temp) 
	                {
	                    arrInteger[startIndex+j] = arrInteger[startIndex+j - increment];
	                    j = j - increment;
	                }
	                arrInteger[startIndex+j] = temp;
	            }
	            if (increment == 2)
	                increment = 1;
	            else
	                increment *= (5.0 / 11);
	 
	        }
	        
		
		//once shell sort is completed, submit the chunk to merger as its sorted
		submitChunkToMergerInteger(startIndex,endIndex);
	}
	
	public void createShellSortJobFloat(int startIndex, int endIndex) {

		//plus 1 is required to include element at end index in sorting process
		int size = endIndex-startIndex+1;
		
		 int increment = size / 2;
	        while (increment > 0) 
	        {
	            for (int i = increment; i < size; i++) 
	            {
	                int j = i;
	                Float temp = arrFloat[startIndex+i];
	                while (j >= increment && arrFloat[startIndex+j - increment] > temp) 
	                {
	                    arrFloat[startIndex+j] = arrFloat[startIndex+j - increment];
	                    j = j - increment;
	                }
	                arrFloat[startIndex+j] = temp;
	            }
	            if (increment == 2)
	                increment = 1;
	            else
	                increment *= (5.0 / 11);
	 
	        }
	        
		
		//once shell sort is completed, submit the chunk to merger as its sorted
		submitChunkToMergerFloat(startIndex,endIndex);
	}
	public void createShellSortJobString(int startIndex, int endIndex) {

		//plus 1 is required to include element at end index in sorting process
		int size = endIndex-startIndex+1;
		
		 int increment = size / 2;
	        while (increment > 0) 
	        {
	            for (int i = increment; i < size; i++) 
	            {
	                int j = i;
	                String temp = arrString[startIndex+i];
	                while (j >= increment && arrString[startIndex+j - increment].compareTo(temp)>0) 
	                {
	                    arrString[startIndex+j] = arrString[startIndex+j - increment];
	                    j = j - increment;
	                }
	                arrString[startIndex+j] = temp;
	            }
	            if (increment == 2)
	                increment = 1;
	            else
	                increment *= (5.0 / 11);
	 
	        }
	        
		
		//once shell sort is completed, submit the chunk to merger as its sorted
		submitChunkToMergerString(startIndex,endIndex);
	}
	/*********************************************************************
	 * This method performs count sort on the assigned chunk
	 * 
	 * Counting sort is an almost linear algorithm O(n) which can be very
	 * fast if the size of lies within a range and the array consist 
	 * of repeated values
	 *********************************************************************/
	public void createCountSortJobInteger(int startIndex, int endIndex, int minNum, int maxNum) {

		//plus 1 is required to include element at end index in sorting process
		int size = endIndex-startIndex+1;
	        int range = maxNum - minNum + 1;
	 
	        int[] count = new int[range];
	        /** make count/frequency array for each element **/
	        for (int i = 0; i < size; i++)
	            count[arrInteger[startIndex+i] - minNum]++;
	        /** modify count so that positions in final array is obtained **/
	        for (int i = 1; i < range; i++)
	            count[i] += count[i - 1];
	        /** modify original array **/
	        int j = 0;
	        for (int i = 0; i < range; i++)
	            while (j < count[i])
	                arrInteger[startIndex+j++] = i + minNum;
	        	
		//once count sort is completed, submit the chunk to merger as its sorted
		submitChunkToMergerInteger(startIndex,endIndex);
	}

	/****************************************************************
	 * This method submit the array to merger that can finally merge
	 * all the sorted sub-array into 1 single array
	 ***************************************************************/
	private void submitChunkToMergerInteger(int startIndex, int endIndex) {
		
		int i;
		
		//if first job is submitted to the merger stack
		if(top==-1)
		{
			// Copy submitted sub-array parts into the stack array
	        for (i = startIndex; i <= endIndex; i++) {
	                stackInteger[++top] = arrInteger[i];
	        }   
	             
		}
		else
		{
	        //start merging
	        startMergingInteger(startIndex,endIndex);
		}
		
	}
	
private void submitChunkToMergerFloat(int startIndex, int endIndex) {
		
		int i;
		
		//if first job is submitted to the merger stack
		if(top==-1)
		{
			// Copy submitted sub-array parts into the stack array
	        for (i = startIndex; i <= endIndex; i++) {
	                stackFloat[++top] = arrFloat[i];
	        }   
	             
		}
		else
		{
	        //start merging
	        startMergingFloat(startIndex,endIndex);
		}
		
	}
	
private void submitChunkToMergerString(int startIndex, int endIndex) {
	
	int i;
	
	//if first job is submitted to the merger stack
	if(top==-1)
	{
		// Copy submitted sub-array parts into the stack array
        for (i = startIndex; i <= endIndex; i++) {
                stackString[++top] = arrString[i];
        }   
             
	}
	else
	{
        //start merging
        startMergingString(startIndex,endIndex);
	}
	
}

	
	/**********************************************************
	 * This method starts merging process, that uses
	 * a stack data structure to merge the data into a single array,
	 *
	 * Runs until single unified sorted array is created
	 * @param endIndex 
	 * @param startIndex 
	 *********************************************************/
	private void startMergingInteger(int startIndex, int endIndex) {
	
			int size = (top+1)+(endIndex-startIndex+1);
			int i = low;
	        int j = startIndex;
	        int k = 0;
	        
	        Integer[] temp = new Integer[size];
	        
	        // Copy the smallest values from either the left or the right side back
	        // to the original array
	        while (i <= (top) && j <= endIndex) {
	                if (stackInteger[i] <= arrInteger[j]) {
	                        temp[k] = stackInteger[i];
	                        i++;
	                } else {
	                        temp[k] = arrInteger[j];
	                        j++;
	                }
	                k++;
	        }
	        // Copy the rest of the left side of the array into the target array
	        while (i <= (top)) {
	                temp[k] = stackInteger[i];
	                k++;
	                i++;
	        }
	        
	        // Copy the rest of the right side of the array into the target array
	        while (j <= endIndex) {
	                temp[k] = arrInteger[j];
	                k++;
	                j++;
	        }
	        
	        //
	       for(int l = 0;l<temp.length;l++)
	        {
	        	stackInteger[l]=temp[l];
	        }
	       
	       //change the top
	        top=size-1;	     

	}
	
	private void startMergingFloat(int startIndex, int endIndex) {
		
		int size = (top+1)+(endIndex-startIndex+1);
		int i = low;
        int j = startIndex;
        int k = 0;
        
        Float[] temp = new Float[size];
        
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= (top) && j <= endIndex) {
                if (stackFloat[i] <= arrFloat[j]) {
                        temp[k] = stackFloat[i];
                        i++;
                } else {
                        temp[k] = arrFloat[j];
                        j++;
                }
                k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= (top)) {
                temp[k] = stackFloat[i];
                k++;
                i++;
        }
        
        // Copy the rest of the right side of the array into the target array
        while (j <= endIndex) {
                temp[k] = arrFloat[j];
                k++;
                j++;
        }
        
        //
       for(int l = 0;l<temp.length;l++)
        {
        	stackFloat[l]=temp[l];
        }
       
       //change the top
        top=size-1;	     

}

	private void startMergingString(int startIndex, int endIndex) {
		
		int size = (top+1)+(endIndex-startIndex+1);
		int i = low;
        int j = startIndex;
        int k = 0;
        
        String[] temp = new String[size];
        
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= (top) && j <= endIndex) {
                if (stackString[i].compareTo(arrString[j]) <= 0) {
                        temp[k] = stackString[i];
                        i++;
                } else {
                        temp[k] = arrString[j];
                        j++;
                }
                k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= (top)) {
                temp[k] = stackString[i];
                k++;
                i++;
        }
        
        // Copy the rest of the right side of the array into the target array
        while (j <= endIndex) {
                temp[k] = arrString[j];
                k++;
                j++;
        }
        
        //
       for(int l = 0;l<temp.length;l++)
        {
        	stackString[l]=temp[l];
        }
       
       //change the top
        top=size-1;	     

}

	public Integer[] getArr() {
		return arrInteger;
	}

	public void setArr(Integer[] arr) {
		this.arrInteger = arr;
	}
	
	public void printArray()
	{
		if(dataType==DATA_TYPE_INTEGER)
		{
			printArrayInteger();
		}
		else if(dataType==DATA_TYPE_FLOAT)
		{
			printArrayFloat();
		}
		else
		{
			printArrayString();
		}
		
	}
	
	public void printSortedArray()
	{
		if(dataType==DATA_TYPE_INTEGER)
		{
			printSortedArrayInteger();
		}
		else if(dataType==DATA_TYPE_FLOAT)
		{
			printSortedArrayFloat();
		}
		else
		{
			printSortedArrayString();
		}
	}
	
	public void printArrayInteger()
	{
		System.out.println("\n**************************************************\nElements in the array are: ");
		for(int i=0;i<N;i++)
		{
			System.out.println(""+arrInteger[i]);
		}
	}
	
	public void printSortedArrayInteger()
	{
		System.out.println("\n**************************************************\nElements in the array are: ");
		for(int i=0;i<N;i++)
		{
			System.out.println(""+stackInteger[i]);
		}
	}
	
	public void printArrayFloat()
	{
		System.out.println("\n**************************************************\nElements in the array are: ");
		for(int i=0;i<N;i++)
		{
			System.out.println(""+arrFloat[i]);
		}
	}
	
	public void printSortedArrayFloat()
	{
		System.out.println("\n**************************************************\nElements in the array are: ");
		for(int i=0;i<N;i++)
		{
			System.out.println(""+stackFloat[i]);
		}
	}
	
	public void printArrayString()
	{
		System.out.println("\n**************************************************\nElements in the array are: ");
		for(int i=0;i<N;i++)
		{
			System.out.println(""+arrString[i]);
		}
	}
	
	public void printSortedArrayString()
	{
		System.out.println("\n**************************************************\nElements in the array are: ");
		for(int i=0;i<N;i++)
		{
			System.out.println(""+stackString[i]);
		}
	}
	
	public void printArray(Integer[] temp)
	{
		System.out.println("\n**************************************************\nElements in the array are: ");
		for(int i=0;i<temp.length;i++)
		{
			System.out.println(""+temp[i]);
		}	
	}
}
