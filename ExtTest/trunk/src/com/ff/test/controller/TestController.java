package com.ff.test.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.ui.button.Button;
import com.ff.ui.grid.Cell;
import com.ff.ui.grid.Grid;
import com.ff.ui.grid.Row;
import com.ff.ui.store.Store;
import com.ff.ui.window.MessageBoxButton;
import com.ff.ui.window.MessageBoxIcon;
import com.ff.ui.window.Window;

@Scope("prototype")
@Controller("TestCtrl")
public class TestController extends com.ff.controller.Controller{
	Button buttonSize;
	Button buttonMessage;
	Button buttonGrid;
	Store userStore;
	Button buttonWindow;
	Window window1;
	Grid grid;
	 
	
	@Override
	protected void onLoad(){
 
		Row row = newInstance(Row.class);
		Cell cell = newInstance(Cell.class);
		cell.setName("firstname");
		cell.setValue("gigi");
		row.addCell(cell);
		cell = newInstance(Cell.class);
		cell.setName("senority");
		cell.setValue("4");
		row.addCell(cell);
		cell = newInstance(Cell.class);
		cell.setName("sesso.nome");
		cell.setValue("M");
		row.addCell(cell);
		
		//grid.addRow(row);
		 
		
		buttonSize.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {				
				buttonSize.setWidth(200);				
			}
		});
		
		buttonMessage.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {								
				MessageBox.show("PROVA MESSAGE", "Questo è un messaggio di errore", MessageBoxButton.YES, MessageBoxIcon.ERROR);				 								
			}
		});
		
		buttonWindow.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {								
				window1.show();
			}
		});
		
		buttonGrid.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {				
				Row row = newInstance(Row.class);
				Cell cell = newInstance(Cell.class);
				cell.setName("firstname");
				cell.setValue("gianni");
				row.addCell(cell);
				cell = newInstance(Cell.class);
				cell.setName("senority");
				cell.setValue("422");
				row.addCell(cell);
				
				grid.addRow(row);
			}
		});

		//userStore.fetch();
	}

}
