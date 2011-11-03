/* Window.js*/

zkext.ui.window.Window = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		 
	},
		 	
	configure_:function(){
		this.$supers('configure_');		
		var config = this.getInitialConfig();
		 
	},
	
	window:null,
	
	createExt_:function(){
		var wgt = this;
		this.newInstance('Ext.window.Window');
		this.window = this.ext_;
		this.ext_=null;
		Ext.onReady(function(){
		//	wgt.window.show();	 
		});		
	},
	
	show:function(){
		var wgt = this;
		this.window.destroy();
		this.newInstance('Ext.window.Window');
		this.window = this.ext_;
		this.ext_=null;
		wgt.window.show();	 
	}
	
	 
	 
});