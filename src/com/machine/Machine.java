package com.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.Scheduller;
import com.job.*;

/*
 * One machine
 */
public class Machine extends Scheduller {
	
	private HashMap<Integer, Job> jobs;
	private List<Job> sched;
	private int standard; // 0 = Cmax or F, 1 = Lmax, 2 = T; 3 = Nt
	
	public Machine(){
		standard = 0;
		jobs = new HashMap<Integer, Job>();
		sched = new ArrayList<Job>();
	}
	
	public Machine(int std){
		standard = std;
		jobs = new HashMap<Integer, Job>();
		sched = new ArrayList<Job>();
	}
	
	public void fille(){
		int key = 0;
		for(Iterator<Job> ite = sched.iterator(); ite.hasNext();){
			Job current = ite.next();
			jobs.put(key, current);
			key++;
		}	
	}
	
	public void spt(){
		
		Collections.sort(sched, new Comparator<Job>(){
			@Override
			public int compare(Job j1, Job j2){
				return 0;
			}
		});		
		fille();	
	}
	
	
	public void edd(){
		Collections.sort(sched, new Comparator<Job>(){
			@Override
			public int compare(Job j1, Job j2){			
				return  j1.getD().compareTo(j2.getD());
			}
		});	
		fille();
	}

	public void HodgsonAndMoore(){
		List<Job> lh = new ArrayList<Job>();
		List<Job> tmp = new ArrayList<Job>();
		
		edd();
		sched.clear();
		
		int sum = 0;
		for(Iterator<Job> ite = sched.iterator(); ite.hasNext();){
			Job current = ite.next();
			tmp.add(current);
			sum += current.getP();
			
			
			for(Iterator<Job> ite1 = tmp.iterator(); ite.hasNext();){
				Job job = ite1.next();
				
				if(job.getD() < sum){
					Job high = longestJob(tmp);
					lh.add(high);
					tmp.remove(high);
					sum -= high.getP();
				}
			}
		}	
		
		sched.addAll(tmp);
		sched.addAll(lh);
		fille();
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
				System.out.println("Not implemented yet");
		}
	}
}
