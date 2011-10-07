package com.ff.cache;

import org.apache.commons.collections.map.LRUMap;



public class Cache<T>{
	LRUMap cacheMap = new LRUMap();
	
	public Cache(){
		
	}

	public synchronized void put(String key, T value){
		addElement(key, value);
	}
	
	public synchronized T get(String key){
		CacheElement el = (CacheElement)cacheMap.get(key);
		if(el!=null){
			return (T)el.getObjectValue();	
		}
		return null;
	}
	
	protected synchronized void addElement(Object key,Object value) {

		int index;
		Object obj;

		 
		// get the entry from the table
		obj = cacheMap.get(key);

		// If we have the entry already in our table
		//then get it and replace only its value.
		if (obj != null) {
			CacheElement element;
	
			element = (CacheElement) obj;
			element.setObjectValue(value);
			element.setObjectKey(key);
			element.touch();
			return;
		}
		//cacheMap.orderedMapIterator().
		
		CacheElement element = new CacheElement();			
		element.setObjectValue(value);
		element.setObjectKey(key);
		element.touch();
		cacheMap.put(key, element);
	}
}
