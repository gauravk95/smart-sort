package com.finalproj.main;

import java.awt.Component;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart {
	
	private JFreeChart barChart;
	private ArrayList<Long> execTime;
	
	
	public BarChart(String chartTitle ,ArrayList<Long> exT) {
	    
		this.execTime= exT;
	    
		this.barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "Algorithm Name",            
	         "Execution Time(ns)",            
	         createDataset(),          
	         PlotOrientation.HORIZONTAL,           
	         false, true, false); 
	      
	   }
	
	public ChartPanel createBarChart()
	{
		ChartPanel chartPanel = new ChartPanel( barChart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	      chartPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
	      return chartPanel;
	}
	   
	   private CategoryDataset createDataset( ) {
	    
	      final String category = "Algorithm"; 
	      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

	      for(int i=SortingController.INSERTION_SORT;i<=SortingController.ADAPTIVE_SORT;i++)
	      {
	    	  dataset.addValue(execTime.get(i-1),category,SortingController.getSortingAlgorithmName(i));
	      } 
	      
	      return dataset; 
	   }
}