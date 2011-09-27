/* Spinner.js*/

zkext.ui.form.Spinner= zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
							 
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Spinner');
	} 	
});