package com.ff.ui.layout;

 
import java.util.HashMap;
import java.util.Map;

import com.ff.annotation.Property;
import com.ff.ui.container.Container;

public class Layout extends com.ff.AbstractComponent{
	
	protected Container realParent;
	
	@Property
	protected String type;
	
	/**
	 * Tolgo il layout dalla gerarchia dei figli.
	 * Il layout non deve essere renderizzato in quanto è un attributo del padre
	 */
	@Override
	public void setParent(org.zkoss.zk.ui.Component cmp) {
		 org.zkoss.zk.ui.AbstractComponent parent = ( org.zkoss.zk.ui.AbstractComponent)cmp;
		 
		if(parent instanceof Container){
			realParent = (Container)parent;
			realParent.setLayout(this);		

			this._init();
		}else{
			super.setParent(cmp);
		}
	}
	
	
	
	 
	


	public Map getModel(){
		Map map = new HashMap();
		for(String key:properties.keySet()){
			map.put(key, getPropertyValue(properties.get(key)));
		}
		return map;
	}
	

	public Container getRealParent() {
		return realParent;
	}

	public void setRealParent(Container realParent) {
		this.realParent = realParent;
	}
	
	
	
}