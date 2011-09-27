/* Display.js*/

zkext.ui.form.Display = zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');								 
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Display');
	} 	
});