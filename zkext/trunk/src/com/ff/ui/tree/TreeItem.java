package com.ff.ui.tree;

import com.ff.Component;
import com.ff.annotation.Property;

public class TreeItem extends Component{

	@Property 
	private String itemId;
	@Property	
	private String text;
	@Property
	private Boolean expanded;
	@Property
	private Boolean root;
	@Property
	private Boolean leaf;
	@Property
	private Boolean expandable;
	@Property
	private Boolean checked;
	@Property
	private String icon;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public Boolean isRoot() {
		return root;
	}
	public void setRoot(Boolean root) {
		this.root = root;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public Boolean getExpandable() {
		return expandable;
	}
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	
}
