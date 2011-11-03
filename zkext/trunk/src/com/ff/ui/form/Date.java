package com.ff.ui.form;

import java.text.SimpleDateFormat;

public class Date extends Picker{
	SimpleDateFormat informat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat outformat = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setValue(String value){
		try{
			java.util.Date date = informat.parse(value);
			super.setValue(outformat.format(date));
		}catch(Exception e){
			
		}
		
	}
}
