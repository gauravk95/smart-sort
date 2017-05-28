package com.finalproj.main.DataModels;

import com.finalproj.main.GlobalValuesAndConstants;

/*********************************************************************************
 * Holds a single Row as an array of columns, consists methods to operate on them
 * @author Gaurav Kumar
 *********************************************************************************/
public class RecordRow{
	
	private String[] cols;
	private Integer compInteger;
	private Float compFloat;
	private String compString;
	
	
	public RecordRow(String cols,String colRegex,boolean firstline) {
		
		if(firstline && cols!=null)
		this.cols = cols.split(colRegex);
		else
		{
			this.cols = new String[]{"Default Col"};
		}
	}
	
	public RecordRow(String cols,String colRegex) throws NumberFormatException {
		this.cols = cols.split(colRegex);
		
		if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_INT)
		{
			//integer
				this.compInteger = Integer.parseInt(this.cols[GlobalValuesAndConstants.COL_INDEX]);
				
		}
		else if(GlobalValuesAndConstants.DATA_TYPE_INDEX==GlobalValuesAndConstants.DATA_TYPE_FLOAT)
		{
				this.compFloat = Float.parseFloat(this.cols[GlobalValuesAndConstants.COL_INDEX]);
					
		}
		else
		{
			//string
			this.compString = this.cols[GlobalValuesAndConstants.COL_INDEX];
		}
	}
	
	
	
	public String[] getCols() {
		return cols;
	}



	public void setCols(String[] cols) {
		this.cols = cols;
	}



	public Integer getCompInteger() {
		return compInteger;
	}



	public void setCompInteger(Integer compInteger) {
		this.compInteger = compInteger;
	}



	public Float getCompFloat() {
		return compFloat;
	}



	public void setCompFloat(Float compFloat) {
		this.compFloat = compFloat;
	}



	public String getCompString() {
		return compString;
	}



	public void setCompString(String compString) {
		this.compString = compString;
	}



	public String[] getMultipleCols() {
		return cols;
	}
	public void setMultipleCols(String[] cols) {
		this.cols = cols;
	}
	
	public String getSingleCol(int colIndex) {
		return cols[colIndex];
	}
	public void setSingleCol(String col,int colIndex) {
		this.cols[colIndex] = col;
	}
	
	
/* Returns the string representation of this Row*/
 @Override
 public String toString() {
     
	 String temp ="";
		for(int i=0;i<cols.length;i++)
		{
			if(i==cols.length-1)
				temp+=cols[i];
			else
				temp+=cols[i]+GlobalValuesAndConstants.COL_DELM;		
			
		}
		
		return temp;
	 
 }
	
}
