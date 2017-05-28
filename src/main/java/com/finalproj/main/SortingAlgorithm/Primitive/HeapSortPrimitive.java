package com.finalproj.main.SortingAlgorithm.Primitive;

public class HeapSortPrimitive {
	
	private int N;
	
	private Integer[] numbers;
	
	private Float[] numFloat;
    
    private String[] numString;
	
	/*************************INTEGER************************/
    /* Sort Function */
    public Integer[] sort(Integer arr[])
    {     
    	this.numbers = arr;
        heapify(numbers);        
        for (int i = N; i > 0; i--)
        {
            swap(numbers,0, i);
            N = N-1;
            maxheap(numbers, 0);
        }
        
        return numbers;
    }     
    /* Function to build a heap */   
    public void heapify(Integer numbers[])
    {
        N = numbers.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(numbers, i);        
    }
    /* Function to swap largest element in heap */        
    public void maxheap(Integer numbers[], int i)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && numbers[left] > numbers[i])
            max = left;
        if (right <= N && numbers[right] > numbers[max])        
            max = right;
 
        if (max != i)
        {
            swap(numbers, i, max);
            maxheap(numbers, max);
        }
    }    
    /* Function to swap two numbers in an array */
    public void swap(Integer numbers[], int i, int j)
    {
        Integer tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp; 

    }
    
    
    /*************************FLOAT************************/
    /* Sort Function */
    public Float[] sort(Float arr[])
    {     
    	this.numFloat = arr;
        heapify(numFloat);        
        for (int i = N; i > 0; i--)
        {
            swap(numFloat,0, i);
            N = N-1;
            maxheap(numFloat, 0);
        }
        
        return numFloat;
    }     
    /* Function to build a heap */   
    public void heapify(Float numFloat[])
    {
        N = numFloat.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(numFloat, i);        
    }
    /* Function to swap largest element in heap */        
    public void maxheap(Float numFloat[], int i)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && numFloat[left] > numFloat[i])
            max = left;
        if (right <= N && numFloat[right] > numFloat[max])        
            max = right;
 
        if (max != i)
        {
            swap(numFloat, i, max);
            maxheap(numFloat, max);
        }
    }    
    /* Function to swap two numbers in an array */
    public void swap(Float numFloat[], int i, int j)
    {
        Float tmp = numFloat[i];
        numFloat[i] = numFloat[j];
        numFloat[j] = tmp; 

    }
    
    /*************************STRING************************/
    /* Sort Function */
    public String[] sort(String arr[])
    {     
    	this.numString = arr;
        heapify(numString);        
        for (int i = N; i > 0; i--)
        {
            swap(numString,0, i);
            N = N-1;
            maxheap(numString, 0);
        }
        
        return numString;
    }     
    /* Function to build a heap */   
    public void heapify(String numString[])
    {
        N = numString.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(numString, i);        
    }
    /* Function to swap largest element in heap */        
    public void maxheap(String numString[], int i)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && (numString[left].compareTo(numString[i])>0))
            max = left;
        if (right <= N && (numString[right].compareTo(numString[max])>0))        
            max = right;
 
        if (max != i)
        {
            swap(numString, i, max);
            maxheap(numString, max);
        }
    }    
    /* Function to swap two numbers in an array */
    public void swap(String numString[], int i, int j)
    {
        String tmp = numString[i];
        numString[i] = numString[j];
        numString[j] = tmp; 

    }
 
}
