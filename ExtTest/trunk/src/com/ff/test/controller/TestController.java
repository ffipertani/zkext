package com.ff.test.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.factory.UiFactory;
import com.ff.ui.button.Button;
import com.ff.ui.grid.Cell;
import com.ff.ui.grid.Grid;
import com.ff.ui.grid.Row;

@Scope("prototype")
@Controller("TestCtrl")
public class TestController extends JJController{
	Button button;
	Button buttone;
	
	Grid grid;
	
	@Override
	protected void onLoad(){
		 
		Row row = newIstance(Row.class);
		Cell cell = newIstance(Cell.class);
		cell.setName("firstname");
		cell.setValue("gigi");
		row.addCell(cell);
		cell = newIstance(Cell.class);
		cell.setName("senority");
		cell.setValue("4");
		row.addCell(cell);
		
		grid.addRow(row);
		 
		
		button.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {				
				button.setWidth(100);
			}
		});
		
		buttone.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {				
				Row row = newIstance(Row.class);
				Cell cell = newIstance(Cell.class);
				cell.setName("firstname");
				cell.setValue("gianni");
				row.addCell(cell);
				cell = newIstance(Cell.class);
				cell.setName("senority");
				cell.setValue("422");
				row.addCell(cell);
				
				grid.addRow(row);
			}
		});

		
	}

}
