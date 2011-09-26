/* Label.js*/

zkext.ui.form.Label = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		text:function(val){
			this.setProperty("text","setText",val);
		}
	},
	
	configure_:function(){
		this.$supers('configure_');		
		 		
		this.createExt('Ext.form.Label');
		//this.createExt('Ext.toolbar.TextItem');
		
	} 	
});