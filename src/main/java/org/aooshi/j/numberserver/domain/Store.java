package org.aooshi.j.numberserver.domain;

import java.util.Date;

public class Store {

	long id;
	long v;
	int step;
	Date createtime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getV() {
		return v;
	}
	public void setV(long v) {
		this.v = v;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
