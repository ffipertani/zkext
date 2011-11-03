package com.ff.utils;

import java.util.Map;

import com.ff.model.Model;

public class MapUtils {
	
	public static Object getValue(Map object, String key){
		try{
			String[] names = key.split("\\.");		
			String name = names[0];
					
			if(names.length>1){
				String children = key.substring(key.indexOf(".")+1);	
				Map child = (Map)object.get(name);
				return getValue(child,children);
			}else{
				return object.get(name);
			}
		}catch(Exception e){
			System.out.println("Errore " + key + " --  " + object); 
			return null;
		}
	}
	
	public static void setValue(Map object, String key, Object value){
		try{
			String[] names = key.split("\\.");		
			String name = names[0];
					
			if(names.length>1){
				String children = key.substring(key.indexOf(".")+1);	
				Map child = (Map)object.get(name);
				
				
				if(child == null){
					Class c = object.getClass();
					child = (Map)c.newInstance();
					object.put(name, child);					
				}
				setValue(child,children,value);
			}else{
				object.put(name,value);
			}
		}catch(Exception e){
			System.out.println("Errore " + key + " --  " + object); 			
		}
	}
}
