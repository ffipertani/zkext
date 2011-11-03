/* Row.js*/

zkext.ui.grid.Rows = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		 
	},	
	configure_:function(){	
		this.$supers('configure_',arguments);		
		 
	},
	onChildAdded_:function(wgt){
		this.$supers('onChildAdded_',arguments);
		if(this.parent!=null){
			this.parent._addRow(wgt);
		}
	},
	
	onChildRemoved_:function(wgt){
		this.$supers('onChildRemoved_',arguments);
		if(this.parent!=null){			 
			var index = wgt.getIndex();
			 
			this.parent._deleteRow(index);
		}
	}
});