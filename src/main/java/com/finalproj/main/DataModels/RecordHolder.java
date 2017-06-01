package com.finalproj.main.DataModels;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.finalproj.main.MainController;
import com.finalproj.main.SortingController;
import com.finalproj.main.GlobalValuesAndConstants;
import com.finalproj.main.SortingAlgorithm.NonPrimitive.CountingSort;
import com.finalproj.main.SortingAlgorithm.NonPrimitive.HeapSort;
import com.finalproj.main.SortingAlgorithm.NonPrimitive.InsertionSort;
import com.finalproj.main.SortingAlgorithm.NonPrimitive.MergeSort;
import com.finalproj.main.SortingAlgorithm.NonPrimitive.QuickSort;
import com.finalproj.main.SortingAlgorithm.NonPrimitive.RadixSort;
import com.finalproj.main.SortingAlgorithm.NonPrimitive.ShellSort;
import com.finalproj.main.SortingAlgorithm.Primitive.CountingSortPrimitive;
import com.finalproj.main.SortingAlgorithm.Primitive.HeapSortPrimitive;
import com.finalproj.main.SortingAlgorithm.Primitive.InsertionSortPrimitive;
import com.finalproj.main.SortingAlgorithm.Primitive.MergeSortPrimitive;
import com.finalproj.main.SortingAlgorithm.Primitive.QuickSortPrimitive;
import com.finalproj.main.SortingAlgorithm.Primitive.RadixSortPrimitive;
import com.finalproj.main.SortingAlgorithm.Primitive.ShellSortPrimitive;
import com.finalproj.main.SortingAlgorithm.Primitive.SmartSort;

/**********************************************************************************
 * Holds N records/rows as a list of RecordRow, consists methods to  operate on them 
 * @author Gaurav Kumar
 **********************************************************************************/

public class RecordHolder {
	
	private ArrayList<RecordRow> rows;
	
	private RecordRow colNames;
	
	private Integer[] intArr;
	private Float[] fltArr;
	private String[] strArr;
	
	public RecordHolder()
	{
		
	}
	
	public RecordHolder(String trows,String rowRegex,String colRegex) {
		String rowslist[] = trows.split(rowRegex);
		this.rows = new ArrayList<RecordRow>();
		
		//initialize the column name list,always string
		this.colNames = new RecordRow(rowslist[0],colRegex,true);
	
		try
		{
			//initialize the actual data
			for(int i =1;i<rowslist.length;i++)
			{
				this.rows.add(new RecordRow(rowslist[i],colRegex));
			}
			
			//initialize the array based on the datatype
			if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
			{
				this.intArr = getIntegerArray();
			}else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
			{
				this.fltArr = getFloatArray();
			}
			else
			{
				this.strArr = getStringArray();
			}
		
		
		}catch(NumberFormatException e)
		{
			System.err.println("ERROR: Cannot convert String into Integer/Float, please choose String datatype");
		}
	}
	
	public RecordRow getSingleRow(int rowIndex) {
		return rows.get(rowIndex);
	}
	public void setSingleRow(RecordRow row,int rowIndex) {
		this.rows.add(rowIndex,row);
	}
	
	public void setMultipleRows(ArrayList<RecordRow> rowlist)
	{
		this.rows = rowlist;
	}
	
	
	public void setMultipleRows(String trows,String rowRegex,String colRegex) {
		String rowslist[] = trows.split(rowRegex);
		this.rows = new ArrayList<RecordRow>();
		
		for(int i =0;i<rowslist.length;i++)
		{
			this.rows.add(new RecordRow(rowslist[i],colRegex));
		}
	}
	
	public ArrayList<RecordRow> getMultipleRows() {
		return this.rows;
	}
	
	public String getMultipleRowsInString()
	{
		String temp ="";
		for(int i=0;i<rows.size();i++)
		{
			if(i==rows.size()-1)
				temp+=rows.get(i).toString();
			else
				temp+=rows.get(i).toString()+GlobalValuesAndConstants.ROW_DELM;
					
					
		}
				
		return temp;
	}
	
	
	public RecordRow getColNames() {
		return colNames;
	}

	public void setColNames(RecordRow colNames) {
		this.colNames = colNames;
	}

/* Returns the string representation of this multiple Row*/
@Override
public String toString() {
		 
 String temp ="";
	for(int i=0;i<rows.size();i++)
	{
		if(i==rows.size()-1)
			temp+=rows.get(i).toString();
		else
			temp+=rows.get(i).toString()+GlobalValuesAndConstants.ROW_DELM;
				
				
	}
			
	return temp;
}
	 
	 public void sortRecords(int code)
		{
		 
		 if(rows.size()>0)
		 {
			 
			 
			//decide which data type comparison is required
			 /*
			  * Comparator<RecordRow> comparator = null;
				if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
				{
					//integer
					comparator = new Comparator<RecordRow>() {
					    @Override
					    public int compare(RecordRow a, RecordRow b) {
					    	
					    	int res = 0;
					    	
					    	if(a.getCompInteger()>b.getCompInteger())
					    		res = 1;
					    	else if(a.getCompInteger()<b.getCompInteger())
					    		res = -1;
					    	
					        return GlobalValuesAndConstants.SORT_DIRECTION*res;
					    }
					};
				}
				else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
				{
					//float
					comparator = new Comparator<RecordRow>() {
					    @Override
					    public int compare(RecordRow a, RecordRow b) {
					    	
					    	int res = 0;
					    	
					    	if(a.getCompFloat()>b.getCompFloat())
					    		res = 1;
					    	else if(a.getCompFloat()<b.getCompFloat())
					    		res = -1;
					    	
					        return GlobalValuesAndConstants.SORT_DIRECTION*res;
					    }
					};
				}
				else
				{
					//string
					comparator = new Comparator<RecordRow>() {
					    @Override
					    public int compare(RecordRow a, RecordRow b) {
					        return GlobalValuesAndConstants.SORT_DIRECTION*(a.getCompString().compareTo(b.getCompString()));
					    }
					};
					
				}
				
				*/
			 
			 //TODO: assign appropriate algorithm to appropriate cases
			 
			 switch(code)
			 {
			 case SortingController.TIM_SORT:
					 
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 Arrays.sort(this.intArr);
					 }
					 else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
					 {
						 Arrays.sort(this.fltArr);
					 }
					 else
					 {
						 Arrays.sort(this.strArr);
					 }
				 break;
				 
			 case SortingController.INSERTION_SORT:
				  //InsertionSort.sort(rows,comparator);
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 new InsertionSortPrimitive().sort(this.intArr);
					 }
					 else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
					 {
						 new InsertionSortPrimitive().sort(this.fltArr);
					 }
					 else
					 {
						 new InsertionSortPrimitive().sort(this.strArr);
					 }
				 break;
				 
			 case SortingController.MERGE_SORT:
				//new MergeSort<RecordRow>().sort(rows,comparator);
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 new MergeSortPrimitive().sort(this.intArr);
					 }
					 else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
					 {
						 new MergeSortPrimitive().sort(this.fltArr);
					 }
					 else
					 {
						 new MergeSortPrimitive().sort(this.strArr);
					 }
				 break;
				 
			 case SortingController.QUICK_SORT:
				 //new QuickSort<RecordRow>().sort(rows,comparator);
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 new QuickSortPrimitive().sort(this.intArr);
					 }
					 else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
					 {
						 new QuickSortPrimitive().sort(this.fltArr);
					 }
					 else
					 {
						 new QuickSortPrimitive().sort(this.strArr);
					 }
				 break;
				 
			 case SortingController.HEAP_SORT:
				 //new HeapSort<RecordRow>().sort(rows,comparator);
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 new HeapSortPrimitive().sort(this.intArr);
					 }
					 else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
					 {
						 new HeapSortPrimitive().sort(this.fltArr);
					 }
					 else
					 {
						 new HeapSortPrimitive().sort(this.strArr);
					 }
				 break;
				 
			 case SortingController.SHELL_SORT:
				 //ShellSort.sort(rows,comparator);
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 new ShellSortPrimitive().sort(this.intArr);
					 }
					 else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
					 {
						 new ShellSortPrimitive().sort(this.fltArr);
					 }
					 else
					 {
						 new ShellSortPrimitive().sort(this.strArr);
					 }
				 break;
				 
			 case SortingController.COUNTING_SORT:
				 //this.rows=CountingSort.sort(rows);
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 new CountingSortPrimitive().sort(this.intArr);
					 }
					 
				 break;
				 
			 case SortingController.RADIX_SORT:
				 //RadixSort.sort(rows);
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 new RadixSortPrimitive().sort(this.intArr);
					 }
					
				 break;
				 
			 case SortingController.ADAPTIVE_SORT:
				 
					 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
					 {
						 new SmartSort(this.intArr).sort();
					 }
					 else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
					 {
						 new SmartSort(this.fltArr).sort();
					 }
					 else
					 {
						 new SmartSort(this.strArr).sort();
					 }
				 break;
				 
			 }
		 }
		 else
		 {
			 System.err.println("ERROR: Cannot perform sort!");
		 }
		 
			
		}
	
	public Integer[] getIntegerArray()
	{
		Integer[] temp = new Integer[this.rows.size()];
		
		for(int i = 0;i<rows.size();i++)
		{
			temp[i] = rows.get(i).getCompInteger();
		}
		
		return temp;
	}
	
	
	public String[] getStringArray()
	{
		String[] temp = new String[this.rows.size()];
		
		for(int i = 0;i<rows.size();i++)
		{
			temp[i] = rows.get(i).getCompString();
		}
		
		return temp;
	}
	
	
	public Float[] getFloatArray()
	{
		Float[] temp = new Float[this.rows.size()];
		
		for(int i = 0;i<rows.size();i++)
		{
			temp[i] = rows.get(i).getCompFloat();
		}
		
		return temp;
	}
	
	public void writeSmartSortResults()
	{
		//write the sort result to a file
		 if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
		 {
			SmartSort temp = new SmartSort(this.intArr);
			temp.sort();
			temp.writeSortedArrayInteger();
		 }
		 else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
		 {
			SmartSort temp = new SmartSort(this.fltArr);
			temp.sort();
			temp.writeSortedArrayFloat();
		 }
		 else
		 {
			SmartSort temp = new SmartSort(this.strArr);
			temp.sort();
			temp.writeSortedArrayString();
		 }
		 
	}
	
}
