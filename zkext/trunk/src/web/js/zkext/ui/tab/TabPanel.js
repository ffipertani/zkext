/* TabPanel.js*/

zkext.ui.tab.TabPanel = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		 
	},
		 	
	configure_:function(){
		this.$supers('configure_');		
		var config = this.getInitialConfig();					 
	},
	
	createExt_:function(){		
		this.newInstance('Ext.tab.Panel');			
	} 
	 
});