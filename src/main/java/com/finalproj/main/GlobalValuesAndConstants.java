package com.finalproj.main;

/**************************************************************************************
 * Map Class which extends MaReduce.Mapper class
 * Map is passed a N lines at a time, it splits the line based o delem
 * and generated the token which are output by map with value as one to be consumed
 * by reduce class
 * @author Gaurav Kumar
 *************************************************************************************/
public class GlobalValuesAndConstants{
	 
	public static final int DATA_TYPE_INT = 1;
	public static final int DATA_TYPE_FLOAT = 2;
	public static final int DATA_TYPE_STRING = 0;
	
    public static int COL_INDEX = 0;
    
    //2 for String, by default
    public static int DATA_TYPE_INDEX = 0;
    
    //1 means ascending, -1 means descending
    public static int SORT_DIRECTION = 1;
    
    public static final String COL_DELM = ",";
    public static final String ROW_DELM = "\n";
    
    public static String records="";
        

}
