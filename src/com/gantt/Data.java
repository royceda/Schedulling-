package com.gantt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.job.Job;

public class Data {
    private ArrayList<Job> jobs;
    
    private int m;
    private int n;
    private int r;

    
    public Data(String filename) throws FileNotFoundException, IOException{
        jobs = new ArrayList<Job>();

        
        BufferedReader br = new BufferedReader(new FileReader(filename));
        
        String delims = " ";
        String parse = br.readLine();
        
        
        //first line
        String[] tmp = parse.split(delims);
        n =Integer.parseInt( tmp[0]);
        m = Integer.parseInt(tmp[1]);
        
        //machine loop
        for(int i = 0; i<m; i++){
        	parse = br.readLine();
        	tmp = parse.split(delims);
            int k =Integer.parseInt( tmp[0]);
        	        	
        	//job loop
        	for(int j=0; j<k; j++){
        		parse = br.readLine();
        		tmp = parse.split(delims);
        		
        		
        		
        	}        	
        }
        
        
        
        
        }
	
}


/* input file
 * 
 * n m 
 * 
 * first machine 
 * n     | number of jobs on this machine
 * 1 2 6 | job 1, start time, end time
 * 6 5 8 | job 6 start 5 end 8
 * 
 * second machine
 * jobs
 * 
 * etc.....
 * 
 */


