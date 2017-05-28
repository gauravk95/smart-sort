package com.finalproj.main.SortingAlgorithm.Primitive;

public class InsertionSortPrimitive {
	
	/***************************************INSERTION SORT********************************/
	//Handles Integer Using Insertion Sort
	public Integer[] sort(Integer[] integers) {  
        int n = integers.length;  
        for (int j = 1; j < n; j++) { 
        
        	/*
        	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
            int key = integers[j];  
            int i = j-1;  
            while ( (i > -1) && ( integers [i] > key ) ) { 
            	/*try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
                integers [i+1] = integers [i];  
                i--;  
            }  
            integers[i+1] = key;  
        }  
        
        return integers;
    }

	//Handles Float Using Insertion Sort
		public  Float[] sort(Float[] integers) {  
	        int n = integers.length;  
	        for (int j = 1; j < n; j++) { 
	        
	        	/*
	        	try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
	            Float key = integers[j];  
	            int i = j-1;  
	            while ( (i > -1) && ( integers [i] > key ) ) { 
	            	/*try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
	                integers [i+1] = integers [i];  
	                i--;  
	            }  
	            integers[i+1] = key;  
	        }  
	        
	        return integers;
	    }
		
		//Handles String Using Insertion Sort
				public  String[] sort(String[] integers) {  
			        int n = integers.length;  
			        for (int j = 1; j < n; j++) { 
			        
			        	/*
			        	try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						*/
			            String key = integers[j];  
			            int i = j-1;  
			            while ( (i > -1) && ( integers [i].compareTo(key) > 0 ) ) { 
			            	/*try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
			                integers [i+1] = integers [i];  
			                i--;  
			            }  
			            integers[i+1] = key;  
			        }  
			        
			        return integers;
			    }

				
}
