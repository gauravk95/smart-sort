package com.finalproj.main;


import java.awt.*;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

//TODO: properly read data and parse using the FileChoser in swing

public class DisplayController {
	
	 private JFrame mainFrame;
	 private JPanel panel ;
	   private JPanel controlPanel;
	   private JPanel statusPanel;
	   private JPanel fileSelectorPanel;
	   private JComboBox colComboBox;
	   private JComboBox typeComboBox;
	   private JComboBox orderComboBox;
	   private ChartPanel barChartPanel;
	   private JLabel progressText;
	   private Label chosenFileText;
	   
	   private final String[] dataTypeList = {"String","Integer","Float"};
	   private final String[] orderTypeList = {"Ascending", "Descending"};
	   
	   private SortingController sController;

	   public DisplayController(){
		   System.out.println("INFO: Initaializing Display Controller");
		   
		
						
	   }
	   
	   public void initSortController()
	   {
		   sController = new SortingController();
		   
		   if(MainController.runType!=1)
		   {// TODO Auto-generated method stub
			sController.startSorting();
		   }
			
		   
			//update the display
			prepareGUI(sController.getColumnNames());
			showJFrame();
			
	   }
	 
	   public void prepareGUI(String[] colNames){
		   
		   System.out.println("INFO: Preparing GUI Components");
	      mainFrame = new JFrame("Smart Sort");
	      mainFrame.setSize(800,550);
	      
	      panel = new JPanel();
	      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	      mainFrame.add(panel);
	      
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	     
	      controlPanel = new JPanel();
	      controlPanel.setLayout(new GridLayout(2,3,20,0));
	      
	      
	      fileSelectorPanel = new JPanel();
	      fileSelectorPanel.setLayout(new FlowLayout());
	      
	      statusPanel = new JPanel();
	      statusPanel.setLayout(new BorderLayout());
	      
	      chosenFileText = new Label("No file selected...");

	      //set the dropdown list
	      colComboBox = new JComboBox();
	      DefaultComboBoxModel model = new DefaultComboBoxModel( colNames );
	      colComboBox.setModel( model );
	      typeComboBox = new JComboBox(dataTypeList);
	      orderComboBox = new JComboBox(orderTypeList);
	      
	      String startNote = "";
	      if(MainController.runType!=1)
	      {
	    	  startNote = "NOTE: Sorting Completed...Below Graph Shows the Execution times!!";
	      }
	      else
	      {
	    	  startNote = "NOTE: Please select/choose a file to start with... ";
	      }
	      progressText=new JLabel(startNote,SwingConstants.CENTER);
	      progressText.setHorizontalAlignment(JLabel.CENTER);
	      progressText.setFont(new Font("Serif", Font.PLAIN, 16));
	   
	      
	  	System.out.println("INFO: Starting the Visualizer...");
	   }
	 
	   
	   
	public void showJFrame(){
		
		//add the file selector panel if in test mode
		if(MainController.runType==1)
		{
			final JFileChooser  fileDialog = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "csv", "txt", "xsl", "rtf");
			fileDialog.setFileFilter(filter);
		      JButton showFileDialogButton = new JButton("Open File");
		      
		      showFileDialogButton.addActionListener(new ActionListener() {
		         
		         public void actionPerformed(ActionEvent e) {
		            int returnVal = fileDialog.showOpenDialog(mainFrame);
		            
		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		               java.io.File file = fileDialog.getSelectedFile();
		               chosenFileText.setText("File Selected : " + file.getName());
		               
		               try {
		            	   
		            	   progressText.setText("NOTE: Please wait ...while we read Input File...!" );
		            	   
							GlobalValuesAndConstants.records = FileHandler.readInputFileString(file.getAbsolutePath());
							
							//generate new sorting controller  initialize with new column names
							sController= new SortingController();
							
							//TODO: update the record column selector one the file is read to be selected to sort
							//FIXEME: the below implementation is not forking properly
							 // getting exiting combo box model
					        DefaultComboBoxModel model = (DefaultComboBoxModel) colComboBox.getModel();
					        // removing old data
					        model.removeAllElements();

					        //System.out.println(sController.getColumnNames().toString());
							for(String str : sController.getColumnNames()) {
								model.addElement(str);
							}
							
							 // setting model with new data
					        colComboBox.setModel(model);
					        
							progressText.setText("NOTE: Successfully read Input File...!" );
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							System.err.println("ERROR: Cannot read file, Number Format Exception...Try again!!");
							progressText.setText("NOTE: Couldn't read Input File...!" );
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.err.println("ERROR: Cannot read file, IO Exception...Try again!!");
							progressText.setText("NOTE: Couldn't read Input File...!" );
						}
		            } else {
		               chosenFileText.setText("No File Selected..." ); 
		               progressText.setText("NOTE: Please select an Input File to proceed...!" );
		            }      
		         }
		      });
		      
		      fileSelectorPanel.add(chosenFileText);
		      fileSelectorPanel.add(showFileDialogButton);
		      fileSelectorPanel.setBorder(new EmptyBorder(5,0,6,0));//top,left,bottom,right
		      
		      panel.add(fileSelectorPanel);
		      panel.add(new JSeparator(SwingConstants.HORIZONTAL));
			
		}
		
	      JButton sortButton = new JButton("Sort Records");
	      sortButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {	        	 
	        
	        	 GlobalValuesAndConstants.COL_INDEX = colComboBox.getSelectedIndex();;
	                
	                GlobalValuesAndConstants.DATA_TYPE_INDEX = typeComboBox.getSelectedIndex();
	                
	                if( orderComboBox.getSelectedIndex()==0)
	                	GlobalValuesAndConstants.SORT_DIRECTION = 1;
	                else if(orderComboBox.getSelectedIndex()==1)
		                GlobalValuesAndConstants.SORT_DIRECTION = -1;
	                
	        	 if((MainController.runType == 1 && GlobalValuesAndConstants.records.compareTo("")!=0) || (MainController.runType == 0)  )
	        	 {
	        		 sortDataAndUpdateDisplay();
	        	 }
	        	 else
	        	 {
	        		 progressText.setText("NOTE: Input records/file empty..Please try again with an appropriate record!");
	        	 }
	                
	         }

	      });
	      
	      
	       controlPanel.add(new Label("Select Column:"));
	        controlPanel.add(new Label("Select DataType:"));
	        controlPanel.add(new Label("Select Sort Direction:"));
	        controlPanel.add(new Label(" "));
	        controlPanel.add(colComboBox);
	        controlPanel.add(typeComboBox);
	        controlPanel.add(orderComboBox);
	        controlPanel.add(sortButton);
	      
	        controlPanel.setBorder(new EmptyBorder(6,0,16,0));
	      //add the control panel to the top
	      panel.add(controlPanel);
	      
	      panel.add(new JSeparator(SwingConstants.HORIZONTAL));
	      //addprogress status text
	      statusPanel.add(progressText);
		  panel.add(statusPanel);
		  panel.add(new JSeparator(SwingConstants.HORIZONTAL));
	      
	      //add the bar chart to the main frame
		  BarChart chart = new BarChart("Algorithm Execution Time: ",sController.getExecTimes());
				
		  barChartPanel=chart.createBarChart();
		  panel.add(barChartPanel);
		  
				      
	      mainFrame.setVisible(true);  
	   }
	
	
	protected void sortDataAndUpdateDisplay() {
		
		progressText.setText("NOTE: Please Wait...While we sort the data an get the results...!!");		
		 panel.revalidate();
	      panel.repaint();
	     
	      SwingUtilities.invokeLater(new Runnable() {
	    	    public void run() {
	    	    	
	    	    	sController.startSorting();
	    	    	progressText.setText("NOTE: Sorting Completed...Below Graph Shows the Execution times!!");	
	    			
	    	    	panel.remove(barChartPanel);
	    			
	    			//add the bar chart to the main frame
	    			  BarChart chart = new BarChart("Algorithm Execution Time: ",sController.getExecTimes());
	    				
	    			  barChartPanel = chart.createBarChart();
	    			  barChartPanel.setVisible(true);
	    			  panel.add(barChartPanel);
	    			  panel.revalidate();
	    		      panel.repaint();
	    	    }
	    	});
		
	}

}
