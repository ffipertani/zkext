/* Hidden.js*/

zkext.ui.form.Hidden= zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
		this.createExt('Ext.form.field.Hidden');					 	
	} 	
});