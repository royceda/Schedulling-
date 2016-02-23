package com.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.Scheduller;
import com.job.*;

/*
 * One machine
 */
public class Machine extends Scheduller {
	
	private ArrayList<Job> sched;
	private List<Job> jobs;
	private int standard; // 0 = Cmax or F, 1 = Lmax, 2 = T; 3 = Nt
	
	public Machine(){
		standard = 0;
		sched    = new ArrayList<Job>();
		jobs     = new ArrayList<Job>();
	}
	
	/**
	 * COnstructor with standard and a List
	 * @param std
	 */
	public Machine(int std, List<Job> listJob){
		standard = std;
		sched    = new ArrayList<Job>();
		jobs     = listJob;
	}
	
	public void fille(){
		for(Iterator<Job> ite = jobs.iterator(); ite.hasNext();){
			Job current = ite.next();
			sched.add(current);
		}	
	}
	
	/**
	 * Scheduling with SPT order (shortest processing time)
	 */
	private void spt(){	
		sched.addAll(jobs);
		Collections.sort(sched, new Comparator<Job>(){
			@Override
			public int compare(Job j1, Job j2){
				return 0;
			}
		});		
	}
	
	/**
	 * Schedulling wth edd order (earlier due date)
	 */
	private void edd(){
		sched.addAll(jobs);
		Collections.sort(sched, new Comparator<Job>(){
			@Override
			public int compare(Job j1, Job j2){			
				return  j1.getD().compareTo(j2.getD());
			}
		});	
	}

	/**
	 * Hogdson Moore algorithm
	 */
	private void HodgsonAndMoore(){
		List<Job> lh  = new ArrayList<Job>();
		List<Job> tmp = new ArrayList<Job>();
		
		edd();
		//tmp.addAll(sched);
		//sched.clear();
		
		int sum = 0;
		for(Iterator<Job> ite = sched.iterator(); ite.hasNext();){
			Job current = ite.next();
			tmp.add(current);
			sum += current.getP();
			
			
			for(Job job: tmp){			
				if(job.getD() < sum){
					Job high = longestJob(tmp);
					lh.add(high);
					tmp.remove(high);
					sum -= high.getP();	
					break;
				}
			}
		}	
		sched.clear();
		sched.addAll(tmp);
		sched.addAll(lh);
		
	}
	
	private Job longestJob(List<Job> l){
		Job higher = new Job();
		for(Iterator<Job> ite = l.iterator(); ite.hasNext();){
			Job current = ite.next();		
			if(higher.getP() < current.getP()){
				higher = current;
			}
		}
		return higher;
	}
	
	@Override
	public void schedule() {
		switch (standard){
			case 1:
				spt();
				break;
			case 2:
				edd();
				break;
			case 3:
				HodgsonAndMoore();
				break;
			default:
				//Branch and bound
				System.out.println("Not implemented yet");
		}
	}
	
	public void print(){
		int i = 0;
		for(Job job: sched){
			String str = ""+i+" : "+job.getName();
			System.out.println(str);
			i++;
		}
		
	}
}
