/* Row.js*/

zkext.ui.grid.Row = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		 
	},	
	configure_:function(){	
		this.$supers('configure_',arguments);		
		 
	},
	toObject:function(){
		var obj = new Object();						
		for (var cell = this.firstChild;cell;cell=cell.nextSibling) {		
			if(cell instanceof zkext.ui.grid.Cell){
				eval("obj."+cell.getName()+"='"+cell.getValue()+"';");								
			}
		}						
		return obj;
	}
	 
});