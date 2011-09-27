/* Page.js*/

zkext.ui.page.Page = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		
	},
	layouted:false,
	configure_:function(){			
		this.$supers('configure_');
		var wgt = this;
		var config = this.getInitialConfig();		
		config.renderTo="pageContainer";
		config.height='100%';
		config.width='100%';		
		config.listeners={
				afterlayout:function(obj,opt){																				 
				}
		}
			
	},
	createExt_:function(){
		var wgt = this;
		
		Ext.onReady(function(){
			wgt.newInstance('Ext.container.Container');	 
			
			Ext.EventManager.onWindowResize(wgt.ext_.doLayout, wgt.ext_);				 
		});
	}, 	
	 
	
	redraw:function(out){
		out.push("<div id='pageContainer' style='width:100%; height:100%'></div>");
	} 
});