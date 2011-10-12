package com.ff.ui.form;

import com.ff.annotation.Property;
import com.ff.ui.button.event.SaveEvent;
import com.ff.ui.panel.Panel;
import com.ff.ui.store.Store;

public class Form extends Panel{
	static{
		registerEvent(SaveEvent.class, Form.class,  SaveEvent.NAME, CE_IMPORTANT);
	}
	
	@Property
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
		store.addForm(this);
	}
	
	
}
