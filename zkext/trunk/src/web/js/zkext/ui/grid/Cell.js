/* Cell.js*/

zkext.ui.grid.Cell = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		 value:function(val){
			 
		 },
		 name:function(val){
			 
		 }
	},	
	configure_:function(){	
		this.$supers('configure_',arguments);		
		 
	}
	 
});