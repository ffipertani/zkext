package com.ff.dao;

import java.util.HashMap;

public class DaoObject extends HashMap<String,Object>{

	@Override
	public Object get(Object key) {
		String strKey = ((String)key).toLowerCase();
		return super.get(strKey);
	}
	
	@Override
	public Object put(String key, Object value) {
		String strKey = ((String)key).toLowerCase();
		return super.put(strKey, value);
	}
	 
}
