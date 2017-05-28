package com.finalproj.main.SortingAlgorithm.NonPrimitive;

import java.util.ArrayList;
import java.util.Comparator;

public class ShellSort {
	
	public static <T>  void sort(ArrayList<T> row,Comparator<? super T> c) 
    {
        int increment = row.size() / 2;
        while (increment > 0) 
        {
            for (int i = increment; i < row.size(); i++) 
            {
                int j = i;
                T temp = row.get(i);
                while (j >= increment && (c.compare(row.get(j - increment), temp) > 0)) 
                {
                    row.set(j,row.get(j - increment));
                    j = j - increment;
                }
                row.set(j, temp);
            }
            
            if (increment == 2)
                increment = 1;
            else
                increment *= (5.0 / 11);
 
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
