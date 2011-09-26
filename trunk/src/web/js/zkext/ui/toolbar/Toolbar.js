/* Toolbar.js*/

zkext.ui.toolbar.Toolbar = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
		
		this.createExt('Ext.toolbar.Toolbar');		 	
	} 
	 	
});