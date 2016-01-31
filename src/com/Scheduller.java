package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.job.Job;

public abstract class Scheduller {
	private HashMap<Integer, Job> jobs;
	private ArrayList<List> sched; //one or many machines
	
	public abstract void schedule();
	
	
	
}
