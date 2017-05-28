package com.finalproj.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.finalproj.main.DataModels.RecordHolder;
import com.finalproj.main.DataModels.RecordRow;

/*****************************************
 * Merges Multiple Record Holder into one
 * @author Gaurav Kumar
 *****************************************/

//TODO: Optimize the solution

public class ParallelMerger {
	
	
	private List<RecordHolder> list;
	
	private int threadCount=0,initialSize;
	
	 private ExecutorService executor;
	
	public ParallelMerger(List<RecordHolder> list)
	{
		this.list = list;
		int cores = 8;
		executor= Executors.newFixedThreadPool(cores);
	}
	
	public RecordHolder merge(){
	
	RecordHolder finalSor = null;
	
		initialSize = list.size();
		
		//initially create N/2 merger threads
		//initMerger();
		
		//repeat the task until only one record is left 
		while(threadCount!=(initialSize-1))
		{
				createMergerThread();
				//System.out.println("Running("+threadCount+","+initialSize+")");	
		}
		
		//shutdown the executor once all the threads for merge is assigned
			executor.shutdown();
		
		//wait for all threads to finish execution
		try {
		  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
		  
		}
			  
			
		finalSor = list.get(0);
		
		return finalSor;
	
	}
	
	private void createMergerThread()
	{
	
		//only if there are 2 or more elements in the list to merge
				if((list.size())>=2)
				{
								
					//ensure that pairs are only used to assign threads
					//get 2 consecutive records
					RecordHolder r1 = (RecordHolder) list.get(0);
					RecordHolder r2 = (RecordHolder) list.get(1);
					

			        list.remove(0);
			        list.remove(0);
			        
			        //counts the number of threads    	 
			        threadCount++;
			        
			      //TODO: change it based on the format of the record holder and the record holder
			       
					Runnable worker = new MergerThread(r1.getMultipleRows(),r2.getMultipleRows(),0);  
			        executor.execute(worker);
			        
				}
				
	}


	class MergerThread implements Runnable { 
	    private ArrayList<RecordRow> ar1;
	    private ArrayList<RecordRow> ar2;
	    private int pos;
	    
	    public MergerThread(ArrayList<RecordRow> ar1,ArrayList<RecordRow> ar2,int pos){  
	        this.ar1 = ar1;
	        this.ar2 = ar2;
	        this.pos = pos;
	    } 
	    
	     public void run() {  
	       	        
	        System.out.println("\n----------------------------------------------\n"+Thread.currentThread().getName()+" INFO: Started Execution, Merge Started");  
	        
	        mergeRecords();               
	        
	        System.out.println(Thread.currentThread().getName()+" INFO: End Execution, Merge Completed"+"\n----------------------------------------------");//prints thread name  
	    }  
	     

	 	private void mergeRecords()
	     {
	     	ArrayList<RecordRow> tempAr = new ArrayList<RecordRow>();
	     	System.out.println("#############  MERGING("+ar1.size()+","+ar2.size()+")  #################");
	         int i = 0;
	         int j = 0;
	      
	         // Copy the smallest values from either the left or the right side back
	         // to the original array
	         while (i < ar1.size() && j < ar2.size()) {
	                 if (ar1.get(i).getSingleCol(GlobalValuesAndConstants.COL_INDEX).compareTo(ar2.get(j).getSingleCol(GlobalValuesAndConstants.COL_INDEX))<0) {
	                         tempAr.add(ar1.get(i));
	                         i++;
	                 } else if (ar1.get(i).getSingleCol(GlobalValuesAndConstants.COL_INDEX).compareTo(ar2.get(j).getSingleCol(GlobalValuesAndConstants.COL_INDEX))==0) {
	                     tempAr.add(ar1.get(i));
	                 	tempAr.add(ar2.get(j));
	                         i++;
	                         j++;
	                 }
	                 else
	                 {
	                 	tempAr.add(ar2.get(j));
	                     j++;
	                 }
	               
	         }
	         // Copy the rest of the left side of the array into the target array
	         while (i < ar1.size()) {
	         		tempAr.add(ar1.get(i));
	                 i++;
	         }
	         
	         // Copy the rest of the left side of the array into the target array
	         while (j < ar2.size()) {
	         		tempAr.add(ar2.get(j));
	                 j++;
	         }

	         
	         //add the merged record to the list
			//TODO: change it based on the format of the record holder and the record holder
	         
	         	RecordHolder tHolder =new RecordHolder();
				tHolder.setMultipleRows(tempAr);
				
				list.add(tHolder);
								
				//once the previous merge is finished, create a new merger
				createMergerThread();
	     	
	     }
	     
	}  

}
