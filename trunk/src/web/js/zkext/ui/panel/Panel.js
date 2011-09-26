/* Panel.js*/

zkext.ui.panel.Panel = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		title:function(val){
			this.setProperty("title","setTitle",val);
		}
	},
	
	configure_:function(){
		this.$supers('configure_');		
		
		this.createExt('Ext.panel.Panel');		
	} 	
});