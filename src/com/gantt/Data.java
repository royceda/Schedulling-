package com.gantt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.job.Job;

public class Data {
    private ArrayList<Job> jobs;
   // private ArrayList<Ressource> ressources;
    
    private int resource;
    private int n;
    private int r;

    
    public Data(String filename) throws FileNotFoundException, IOException{
        jobs = new ArrayList<Job>();

        
        //BufferedReader br = new BufferedReader(new FileReader(filename));
        }
	
}
