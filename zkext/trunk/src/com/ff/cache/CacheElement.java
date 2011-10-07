package com.ff.cache;

import java.util.Date;

public class CacheElement implements Comparable<CacheElement>{
	private Object objectValue;
	private Object objectKey;
	private Date lastHit;
	
	public void touch(){
		lastHit = new Date();
	}
	
	public Object getObjectValue() {
		return objectValue;
	}
	public void setObjectValue(Object objectValue) {
		this.objectValue = objectValue;
	}
	public Object getObjectKey() {
		return objectKey;
	}
	public void setObjectKey(Object objectKey) {
		this.objectKey = objectKey;
	}
	public Date getLastHit() {
		return lastHit;
	}
	public void setLastHit(Date lastHit) {
		this.lastHit = lastHit;
	}
	@Override
	public int compareTo(CacheElement o) {
		if(o.getLastHit().getTime()==lastHit.getTime()){
			return 0;
		}else if(o.getLastHit().getTime()<lastHit.getTime()){
			return 1;
		}
		return -1;
	}
	
	
	
	 
}
