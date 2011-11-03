package com.ff.ui.grid;

import java.util.ArrayList;
import java.util.List;

import com.ff.Component;
import com.ff.annotation.Property;
import com.ff.ui.container.Container;

public class Row extends Container{
	
	@Property
	private int index;
	
	
	 public List<Cell> getCells(){
		 List<Component> children = getChildren();
		 List<Cell> cells = new ArrayList<Cell>();
		 for(Component child:children){
			 if(child instanceof Cell){
				 cells.add((Cell)child);
			 }
		 }
		 return cells;
	 }
	 
	 public void addCell(Cell cell){
		 cell.setParent(this);		 
	 }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	 
	 
}