/* Page.js*/

zkext.ui.page.Page = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		
	},
	
	configure_:function(){			
		this.$supers('configure_');		
		var config = this.getInitialConfig();		
		config.renderTo="pageContainer";
		config.height='100%';
		config.width='100%';	
		 
		this.createExt('Ext.container.Container');
		
		Ext.EventManager.onWindowResize(this.ext_.doLayout, this.ext_);
	},	 
	
	redraw:function(out){
		out.push("<div id='pageContainer' style='margin:0;padding:0;left:0;top:0;position:absolute; width:100%; height:100%'></div>");
	}
	
	
	
	 
});