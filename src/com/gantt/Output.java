package com.gantt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;

import com.job.Job;

public class Output extends ApplicationFrame{

	 public Output(final String title) throws IOException {

		 super(title);
		 
		 IntervalCategoryDataset dataset = createDataset();
		 final JFreeChart chart = createChart(dataset);
		 
		 // add the chart to a panel...
		 final ChartPanel chartPanel = new ChartPanel(chart);
		 chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		 setContentPane(chartPanel); 
	 }

	 
	
	 public IntervalCategoryDataset createDataset() throws IOException {

		 //final Data data = new Data("input.in");
		 final TaskSeriesCollection collection = new TaskSeriesCollection();
		 
		 
		
		 BufferedReader br = new BufferedReader(new FileReader("input1.in"));
	        
	        String delims = " ";
	        String parse = br.readLine();
	        
	        
	        //first line
	        String[] tmp = parse.split(delims);
	        int n =Integer.parseInt( tmp[0]);
	        int m = Integer.parseInt(tmp[1]);
	        
	        //machine loop
	        for(int i = 0; i<m; i++){
	        	parse = br.readLine();
	        	tmp = parse.split(delims);
	            int k =Integer.parseInt( tmp[0]);
	        	        	
	        	
	            String description = "machine "+i;
	            final TaskSeries s1 = new TaskSeries(description);
	            
	            
	            //job loop
	        	for(int j=0; j<k; j++){
	        		parse = br.readLine();
	        		tmp = parse.split(delims);
	        		
	        		String name = tmp[0];
	        		int start = Integer.parseInt(tmp[1]);
	        		int end = Integer.parseInt(tmp[2]);
	        		
	        		
	        		String descriptionj = "job "+j;
	        		s1.add(new Task(descriptionj, new SimpleTimePeriod(start, end)));
	        		     		
	        	}  
	        	collection.add(s1);
	        }
		 
		 return collection;
	    }
	 
	 
	 
	 
	   private JFreeChart createChart(final IntervalCategoryDataset dataset) {
	        final JFreeChart chart = ChartFactory.createGanttChart(
	            "Gantt",  // chart title
	            "Task",              // domain axis label
	            "Date",              // range axis label
	            dataset,             // data
	            true,                // include legend
	            true,                // tooltips
	            false                // urls
	        );    
//	        chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
	        return chart;    
	    }
	
	
}
