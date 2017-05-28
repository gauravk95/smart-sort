package com.finalproj.main.SortingAlgorithm.Primitive;

public class ShellSortPrimitive {
	
	/************************INTEGER************************/
	public Integer[] sort(Integer[] sequence) 
    {
        int increment = sequence.length / 2;
        while (increment > 0) 
        {
            for (int i = increment; i < sequence.length; i++) 
            {
                int j = i;
                int temp = sequence[i];
                while (j >= increment && sequence[j - increment] > temp) 
                {
                    sequence[j] = sequence[j - increment];
                    j = j - increment;
                }
                sequence[j] = temp;
            }
            if (increment == 2)
                increment = 1;
            else
                increment *= (5.0 / 11);
 
        }
        
        return sequence;
    }
	
	
	/************************FLOAT************************/
	public Float[] sort(Float[] sequence) 
    {
        int increment = sequence.length / 2;
        while (increment > 0) 
        {
            for (int i = increment; i < sequence.length; i++) 
            {
                int j = i;
                Float temp = sequence[i];
                while (j >= increment && sequence[j - increment] > temp) 
                {
                    sequence[j] = sequence[j - increment];
                    j = j - increment;
                }
                sequence[j] = temp;
            }
            if (increment == 2)
                increment = 1;
            else
                increment *= (5.0 / 11);
 
        }
        
        return sequence;
    }
	
	
	/************************STRING************************/
	public String[] sort(String[] sequence) 
    {
        int increment = sequence.length / 2;
        while (increment > 0) 
        {
            for (int i = increment; i < sequence.length; i++) 
            {
                int j = i;
                String temp = sequence[i];
                while (j >= increment && (sequence[j - increment].compareTo(temp) > 0)) 
                {
                    sequence[j] = sequence[j - increment];
                    j = j - increment;
                }
                sequence[j] = temp;
            }
            if (increment == 2)
                increment = 1;
            else
                increment *= (5.0 / 11);
 
        }
        
        return sequence;
    }

}
