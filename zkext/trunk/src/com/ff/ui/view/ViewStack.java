package com.ff.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.AbstractComponent;
import com.ff.ui.layout.FitLayout;
import com.ff.ui.panel.Panel;

public class ViewStack extends Panel{

	private List<View> views = new ArrayList();
	private View activeView;
	
	public ViewStack(){
		this.setLayout(newInstance(FitLayout.class));
		this.setStyleHeight("100%");
		this.setStyleWidth("100%");		
	}
	 
	public void createView(String fqn){
		AbstractComponent comp = newInstance(fqn);
		if(comp==null){
			return;
		}
		View thisView;
		if(! (comp instanceof View)){			
			View view = newInstance(View.class);	
			comp.setParent(view);
			thisView = view;							
		}else{
			thisView = (View)comp;
		}
		
		addView(thisView);
	}
	
	public void addView(View view){
		if(activeView!=null){
			activeView.detach();
			//activeView.setParent(null);
		}
		view.setParent(this);
		view.addEventListener("onClose", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {				
				View aview = (View)event.getTarget();
				aview.close();
			}
			
		});
		activeView = view;
		
	}
}
