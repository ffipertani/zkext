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
			this.parent.addRow(wgt);
		}
	}
});