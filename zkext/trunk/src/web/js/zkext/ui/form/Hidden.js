/* Hidden.js*/

zkext.ui.form.Hidden= zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');									 
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Hidden');
	} 	
});