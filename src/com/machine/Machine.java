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
	
	private int standard; // 0 = Cmax or F, 1 = Lmax, 2 = T; 3 = Nt
	
	public Machine(){
		super();
		standard = 0;

	}
	
	/**
	 * COnstructor with standard and a List
	 * @param std
	 */
	public Machine(int std, List<Job> listJob){
		super(listJob);
		standard = std;
	}

	
	
	/**
	 * Scheduling with SPT order (shortest processing time)
	 */
	private void spt(){	
		sched.addAll(jobs);
		Collections.sort(sched, new Comparator<Job>(){
			@Override
			public int compare(Job j1, Job j2){
				return j1.getP().compareTo(j2.getP());
			}
		});		
	}
	
	
	
	/**
	 * Scheduling with WSPT order (shortest processing time)
	 */
	private void wspt(){	
		sched.addAll(jobs);
		Collections.sort(sched, new Comparator<Job>(){
			@Override
			public int compare(Job j1, Job j2){
				double r1 = 1.0*j1.getP()/j1.getW();
				double r2 = 1.0*j2.getP()/j2.getW();
				if(r1 < r2){
					return -1;
				}else if(r1 > r2){
					return 1;
				}else
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
			case 0:
				spt();
				break;
			case 1:
				edd();
				break;
			case 2:
				HodgsonAndMoore();
				break;
			case 3:
				wspt();
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
