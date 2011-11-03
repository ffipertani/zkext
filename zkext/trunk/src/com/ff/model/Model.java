package com.ff.model;

import static com.ff.model.Model.MODEL_ID;

import java.util.Map;
import java.util.Set;

import org.zkoss.json.JSONObject;

public class Model extends JSONObject{
	public static final String MODEL_ID = "__model_id";
	
	public Model(){
		
	}
	
	public Integer getId(){
		if(keySet().contains(MODEL_ID)){
			Object oid = get(MODEL_ID);
			if(oid==null){
				return null;
			}		
			if(oid instanceof String){
				return Integer.parseInt((String)oid);
			}else{
				return (Integer)oid;
			}
		}
		return null;
	}
	
	public Model(Map model){
		Set<String> keys = model.keySet();
		for(String key:keys){
			put(key, model.get(key));
		}
	}
	
	public void merge(Map object){		
		doMerge(this,object);	
	}
	
	private void doMerge(Map map1, Map map2){
		Set<String> keys = (Set) map2.keySet();
		for(String key:keys){
			if(!containsKey(key)){
				map1.put(key, map2.get(key));
			}else{
				Object val = map1.get(key);
				if(val instanceof Map){
					doMerge((Map)val,(Map)map2.get(key));
				}else{
					map1.put(key, map2.get(key));
				}
			}
		}					
	}
}
