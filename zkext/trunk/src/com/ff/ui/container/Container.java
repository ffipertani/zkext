package com.ff.ui.container;

import java.io.IOException;

import org.zkoss.zk.ui.sys.ContentRenderer;

import com.ff.Component;
import com.ff.annotation.Property;
import com.ff.enums.UpdateType;
import com.ff.ui.layout.Layout;

public class Container extends Component{
	private String activeItem;
	private Boolean autoDestroy;
	private String[] bubbleEvents;
	private String defaultType;
	
	@Property
	private String dock;
	@Property
	private Boolean autoScroll=false;
	//defaults
	//items
	 
	@Property(updateClient=UpdateType.NEVER)
	private Layout layout;
	private Boolean suspendLayout;
	
	
	@Override
	protected void renderProperties(ContentRenderer renderer)
			throws IOException {	
		super.renderProperties(renderer);
		
		if(layout!=null){
			render(renderer, "layout", layout.getModel());
		}		
	}
	
	public Layout getLayout() {
		return layout;
	}
	public void setLayout(Layout layout) {
		this.layout = layout;		
	}

	public String getDock() {
		return dock;
	}

	public void setDock(String dock) {
		this.dock = dock;
	}

	public Boolean getAutoScroll() {
		return autoScroll;
	}

	public void setAutoScroll(Boolean autoScroll) {
		this.autoScroll = autoScroll;
	}
	
	
	
}
