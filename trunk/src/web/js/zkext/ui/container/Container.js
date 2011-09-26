/* Container.js*/

zkext.ui.container.Container = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		layout:function(val){				
			this.setProperty("layout",null,val);
		}
	},	
	configure_:function(){	
		this.$supers('configure_',arguments);		
		
	}
	 
});