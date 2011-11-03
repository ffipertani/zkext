package com.ff.entity;

import java.io.InputStream;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import com.ff.cache.Cache;

public class EntityFactory {
	private static Cache<EntityDescriptor> entityCache = new Cache<EntityDescriptor>();
	private static Mapping entityMapping;
	
	private synchronized static void buildMapping(){
		if(entityMapping == null){
			InputStream fs = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/ff/entity/mapping.xml");			 
			entityMapping = new Mapping();
			entityMapping.loadMapping(new InputSource(fs));
		}
	}
	
	public synchronized static EntityDescriptor createEntity(String name){
		try{
			EntityDescriptor ed = entityCache.get(name);
			buildMapping();
			
			if(ed==null){			
				String packageName = name.replaceAll("\\.", "/");
				InputStream testFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(packageName+".xml");
				 
				Unmarshaller unmarshaller = new Unmarshaller(EntityDescriptor.class);
				unmarshaller.setMapping(entityMapping);
				ed = (EntityDescriptor) unmarshaller.unmarshal(new InputSource(testFile));
			
				testFile.close();
				entityCache.put(name, ed);
			}				
			return ed;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
