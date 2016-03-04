package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.job.Job;

public abstract class Scheduller {
	protected List<Job> jobs;
	protected List<Job> sched; //one or many machines

	public Scheduller(){
		sched    = new ArrayList<Job>();
		jobs     = new ArrayList<Job>();
	}


	public Scheduller(List<Job> jobList){
		sched    = new ArrayList<Job>();
		jobs     = jobList;
	}


	public abstract void schedule();



}
