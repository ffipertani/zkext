package com.ff.ui.tree;

import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.annotation.Property;
import com.ff.ui.grid.event.SelectionChangeEvent;
import com.ff.ui.menu.MenuItem;
import com.ff.ui.panel.Panel;

public class TreePanel extends Panel{

	static {		
		registerEvent(SelectionChangeEvent.class, TreePanel.class,  SelectionChangeEvent.NAME, CE_IMPORTANT);
	}
	
	
	private TreeItem selectedItem;
	
	@Property
	private Boolean showRoot = true;

	
	@Override
	public void init() {		
		super.init();
		addEventListener(SelectionChangeEvent.NAME, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				Map data = (Map)event.getData();		
				if(data!=null && data.get("id")!=null){
					String id = (String)data.get("id");
					doSelectItem(id);					
				}else{
					doSelectItem(null);
				}
								
			}
		});
	}
	
	protected void doSelectItem(String id){
		if(id==null){
			selectedItem = null;
		}
		doSelectItemRecursive(id,this.getRoot());
		
	}
	
	
	private void doSelectItemRecursive(String id,TreeItem parent){
		List<TreeItem> items = parent.getChildrenOfClass(TreeItem.class);
		for(TreeItem item:items){
			if(item.getUuid().equals(id)){
				this.selectedItem = item;
				break;
			}else{
				doSelectItemRecursive(id,item);
			}
		}
	}
	
	public TreeItem getRoot(){
		return this.getChildOfClass(TreeItem.class);
	}

	public Boolean getShowRoot() {
		return showRoot;
	}

	public void setShowRoot(Boolean showRoot) {
		this.showRoot = showRoot;
	}

	public TreeItem getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(TreeItem selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	
}
