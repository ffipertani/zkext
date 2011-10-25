package com.ff.utils;

import java.util.Map;

public class MapUtils {
	
	public static Object getValue(String key, Map object){
		try{
			String[] names = key.split("\\.");		
			String name = names[0];
					
			if(names.length>1){
				String children = key.substring(key.indexOf(".")+1);	
				Map child = (Map)object.get(name);
				return getValue(children,child);
			}else{
				return object.get(name);
			}
		}catch(Exception e){
			System.out.println("Errore " + key + " --  " + object); 
			return null;
		}
	}
}
