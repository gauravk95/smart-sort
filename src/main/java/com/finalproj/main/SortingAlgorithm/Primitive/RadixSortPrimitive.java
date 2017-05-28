package com.finalproj.main.SortingAlgorithm.Primitive;

public class RadixSortPrimitive {

	 /** Radix Sort function **/
    public Integer[] sort( Integer[] a)
    {
        int i, m = a[0], exp = 1, n = a.length;
        Integer[] b = new Integer[100000];
        for (i = 1; i < n; i++)
            if (a[i] > m)
                m = a[i];
        while (m / exp > 0)
        {
            int[] bucket = new int[100000];
 
            for (i = 0; i < n; i++)
                bucket[(a[i] / exp) % 10]++;
            for (i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (i = n - 1; i >= 0; i--)
                b[--bucket[(a[i] / exp) % 10]] = a[i];
            for (i = 0; i < n; i++)
                a[i] = b[i];
            exp *= 10;        
        }
        
        return a;
    }    
    
}
