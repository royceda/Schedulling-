package com.job;

import java.util.List;

public class Job{
	private int name;
	private int w; //weight
	private int p; //processing time
	private int r; //release date
	private int d; //due date or deadline
	
	private List<Integer> previous;
	
	
	public Job(){}
	
	public Job(int name, int w, int p, int r, int d){
		this.setName(name);
		this.setW(w);
		this.setP(p);
		this.setR(r);
		this.setD(d);		
	}
	
	
	
	public Integer getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	
	public Integer getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	
	public Integer getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	
	public Integer getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
	public Integer getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	
	
	
	
	
	

}
