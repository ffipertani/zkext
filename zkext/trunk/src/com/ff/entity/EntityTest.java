package com.ff.entity;

import java.io.InputStream;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;


public class EntityTest {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			InputStream fs = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/ff/entity/mapping.xml");
		//	FileInputStream fs = new FileInputStream(new File("./mapping.xml"));			 
			Mapping mapping = new Mapping();
			mapping.loadMapping(new InputSource(fs));
	
			
			InputStream testFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/ff/entity/test.xml");
			 
			Unmarshaller unmarshaller = new Unmarshaller(EntityDescriptor.class);
			unmarshaller.setMapping(mapping);
			EntityDescriptor ed = (EntityDescriptor) unmarshaller.unmarshal(new InputSource(testFile));
		
			testFile.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
