package com.gantt;

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

		 final Data data = new Data("sol.txt");
		 final TaskSeriesCollection collection = new TaskSeriesCollection();
		 
		 
		/* for(Iterator<Ressource> ite1 = data.getRessources().iterator(); ite1.hasNext();){    
			 Ressource res            = ite1.next();           
			 int             br              = res.getB();
			 String        namer        = res.getName();
			 String        description =  namer + " B= "+String.valueOf(br);
	            
			 final TaskSeries s1 = new TaskSeries(description);
	            
	           /* for(Iterator<Job> ite = data.getJobs().iterator() ; ite.hasNext();){
	            	Job job = ite.next();
	            	
	            	String namej =  job.getName(); 
	                int p = job.getP();
	                int start = job.getS();
	                int end = start+p;
	                            
	                int index = Integer.parseInt(res.getName());
	                //int bj = job.getB().get(index); //list ou tableau
	                
	                //String descriptionj = namej+", b="+String.valueOf(bj);
	                String descriptionj = namej;
	                
	                s1.add(new Task(descriptionj,
	                        new SimpleTimePeriod(start, end)));
	            }
	            collection.add(s1);
	        }       */
		 
		 String description = "machine 1";
		 final TaskSeries s1 = new TaskSeries(description);
		 String descriptionj = "job";
		 int start = 1;
		 int end = 6;
		 s1.add(new Task(descriptionj, new SimpleTimePeriod(start, end)));
		 s1.add(new Task("aaa", new SimpleTimePeriod(5, 8)));
		 s1.add(new Task("bbb", new SimpleTimePeriod(3, 7)));
		 collection.add(s1);
		 
		 return collection;
	    }
	 
	 
	 
	 
	   private JFreeChart createChart(final IntervalCategoryDataset dataset) {
	        final JFreeChart chart = ChartFactory.createGanttChart(
	            "RCPSP",  // chart title
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
