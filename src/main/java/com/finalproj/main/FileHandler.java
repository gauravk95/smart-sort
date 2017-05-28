package com.finalproj.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileHandler {

	//TODO: properly read data and parse using the FileChoser in swing
	
	public static String readInputFileString(String path) throws NumberFormatException, IOException
	{
		String inputArray= "";
		
		//open the file to read the input for sorting
				FileInputStream in = null;

			    
			    	   final String dir = System.getProperty("user.dir");
			    	   try {  
					         in = new FileInputStream(path);
					        						     
					         BufferedReader br = new BufferedReader(new InputStreamReader(in));
					     
					         String temp;
					         //read the input
					         while ((temp = br.readLine()) != null) {
					        	 if(temp.compareTo("")!=0)
					        		 inputArray+=temp.trim()+"\n";
					         }
			         
					         br.close();
					         
			    	   } catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	   finally {
				         if (in != null) {
				            in.close();
				         }
	        
	  }
			         return inputArray;
	}
	
	
	public static int writeOutputFile(String path,String res) throws NumberFormatException, IOException
	{
		//open the file to read the input for sorting
				FileOutputStream out = null;

			    
			    	   final String dir = System.getProperty("user.dir");
			    	   try {  
					         out = new FileOutputStream(path,true);
					         BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out));	
					         br.append(res);
					         br.newLine();
					         br.close();
					         
			    	   } catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return -1;
						}
			    	   finally {
				         if (out != null) {
				            out.close();
				         }
	        
			    	   }
			    	   
			         return 1;
	}
	
	//clear the output file
	public static int clearOutputFile(String path) throws IOException
	{
		//open the file to read the input for sorting
		FileOutputStream out = null;

	    
	    	   final String dir = System.getProperty("user.dir");
	    	   try {  
			         out = new FileOutputStream(path);
			         BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out));	
			         br.flush();
			         br.close();
			         
	    	   } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
	    	   finally {
		         if (out != null) {
		            out.close();
		         }
    
	    	   }
	    	   
	         return 1;
	}
	
}
