/* Menu.js*/

zkext.ui.menu.Menu = zk.$extends(zkext.ui.tree.TreePanel,{	 
	$define: {	 
		 
	},
		 	
	configure_:function(){
		this.$supers('configure_');		
		var config = this.getInitialConfig();
		config.collapsedCls = "x-toolbar-default ";
	},
	
	createExt_:function(){		
		this.newInstance('Ext.tree.Panel');			
	},
		 	 
});