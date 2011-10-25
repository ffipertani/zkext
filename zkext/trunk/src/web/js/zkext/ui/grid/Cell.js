/* Cell.js*/

zkext.ui.grid.Cell = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		 value:function(val){
			 
		 },
		 name:function(val){
			 val = val.replace(".",ESCAPE_FIELD);
			 this._name = val; 
		 }
	},	
	configure_:function(){	
		this.$supers('configure_',arguments);		
		 
	}
	 
});