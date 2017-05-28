package com.finalproj.main.SortingAlgorithm.NonPrimitive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.finalproj.main.GlobalValuesAndConstants;
import com.finalproj.main.DataModels.RecordRow;


//TODO: This method is not sorting the records....Fix it

public class InsertionSort{
	
	/***************************************INSERTION SORT********************************/
	//Handles Insertion Sort Generic 
			public static <T>  void sort(ArrayList<T> row,Comparator<? super T> c) {  
				  int n = row.size();  
			        for (int j = 1; j < n; j++) { 
			       
			            T key = row.get(j);  
			            int i = j-1;  
			            while ( (i > -1) && (c.compare(row.get(i),key)>0)) {  
			                
			            	//exchange the records
			            	row.set(i+1,row.get(i));
			            	
			                i--;  
			            } 
			            
			          //exchange the records
		            	row.set(i+1,key);
			           
			        } 
			        
			        //System.out.println("\n****************************SORTED RECORD****************************");
			        //printRecords(row);			        
			      
		    }
		      
			public static <T> void printRecords(ArrayList<T> row)
			{
				
				for(int i=0;i<row.size();i++)
				{
					System.out.println(row.get(i).toString());
				}
			}     
				
}
