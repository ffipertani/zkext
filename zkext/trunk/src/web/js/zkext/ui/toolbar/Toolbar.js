/* Toolbar.js*/

zkext.ui.toolbar.Toolbar = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');					
	},
	
	createExt_:function(){		
		this.newInstance('Ext.toolbar.Toolbar');		
	} 
	 	
});