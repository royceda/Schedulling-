package com.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.job.*;

public class Generator {
	
	public Generator(){}
	
	/*
	 * Generate a list of jobs
	 */
	public List<Job> generateSimple(){
		List<Job> jobs = new ArrayList<Job>();
		int n = 10;
		Random r = new Random();
		for(int i =0; i< n; i++){
			Job tmp = new Job(i, r.nextInt(10), r.nextInt(10),r.nextInt(10), r.nextInt(10));
			jobs.add(tmp);
		}	
		return jobs;
	}
	
	
	/**
	 * Generate an input file
	 * @throws IOException
	 */
	public void generateFile() throws IOException{	
		List<Job> jobs = generateSimple();
		
		String filename = "input.in";
		FileWriter fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw); 
			
		int n = jobs.size();
		int standard = 1;
		
		String str = n+" "+standard;
		bw.write(str);
		bw.newLine();
		for(Iterator<Job> ite = jobs.iterator(); ite.hasNext();){
			Job tmp = ite.next();
			str = tmp.getName()+" "+tmp.getP()+" "+tmp.getW()+" "+tmp.getR()+ " "+tmp.getD();
			bw.write(str);
			bw.newLine();
		}
		bw.close();
	}
	
	
	public List<Job> parse(String filename){	
		return null;
	}
	
	
	public List<Job> generateWithDependencies(){
		return null;
	}
}
