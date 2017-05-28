package com.finalproj.main;

/*****************************************************************
 * The entry point for the SmartSort program,
 * which setup the Hadoop job with Map and Reduce Class
 * 
 * @author Gaurav Kumar
 ******************************************************************/
public class MainController {
	
	public static int runType = 1;//1: indicates always ask user of input file, 0: load file from command line
	
	/**
	 * Main function which calls the run method and passes the args using ToolRunner
	 * @param args Two arguments input and output file paths
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		
		//run the program
		run(args);  
		//System.exit(exitCode);
	}
	
 
	/**
	 * Run method which schedules the Hadoop Job
	 * @param args Arguments passed in main function
	 */
	public static int run(String[] args) throws Exception {
		
		int returnValue = 0;

			DisplayController display = new DisplayController();  
			display.initSortController();

		return returnValue;
	}
}
