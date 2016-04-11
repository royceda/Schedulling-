package com.shop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.Scheduller;
import com.job.Job;
import com.machine.Machine;

public class FlowShop extends Scheduller{

	/*
	 * Here we want to maximize the makespan
	 * 
	 * Check the number of machine
	 * 2 machines, there is an exact method
	 * more than 2, heuristic
	 */


	private int n; //number of machine
	
	public FlowShop(int n, List<Job> jobList){
		super(jobList);
		
		this.n = n;
		//this.proc = new int[n];
	}
	
	
	/*
	* 2 machines
	*/
	private void johnson(){
		ArrayList<Job> U = new ArrayList<Job>();
		ArrayList<Job> V = new ArrayList<Job>();
		
		//Fill U and V
		for(Job tmp: this.jobs){
			if(tmp.p1[0] < tmp.p1[1]){
				U.add(tmp);
			}else{
				V.add(tmp);
			}
		}
		
		//sorting
		Collections.sort(U, new Comparator<Job>(){
			@Override
			public int compare(Job j1, Job j2){
				return j1.getP(0).compareTo(j2.getP(0));
			}
		});
		Collections.sort(V, new Comparator<Job>(){
			@Override
			public int compare(Job j1, Job j2){
				return j2.getP(1).compareTo(j1.getP(1));
			}
		});
		
		
		//Schedule
		for(Job tmp : U){
			sched.add(tmp);
		}		
		for(Job tmp : V){
			sched.add(tmp);
		}
	}

	
	/*
	 * Johnson for 3 machines with domination
	 * we create 2 virtual machine
	 * with p1'=p1+p2 and p2'=p2+p3 if machine 2 is dominated
	 */
	public void dominated(int index){
		
		FlowShop virtual = new FlowShop(this.n, this.jobs);
		
		for(Job tmp : virtual.jobs){
			for(int i=0; i<n; i++){
				int p1 = 0;
				int p2 = 0;
				if(i != index){
					 p1 += tmp.p1[i] + tmp.p1[index];
					 p2 += tmp.p1[index] + tmp.p1[n-i];
				}
			}
		}
		
		virtual.johnson();
		
		for(Job tmp : virtual.sched){
			this.sched.add(tmp);
		}
	}

	
	public void cds(){}

	public void danenbring(){}

	public void branchAndBound(){}

	@Override
	public void schedule() {
		switch (n){
		case 2:
			this.johnson();
			break;
		case 3:
			this.dominated(2); //we suppose it
			break;
		default:
			cds();
			danenbring();
			branchAndBound();
			break;	
		}

	}

}
