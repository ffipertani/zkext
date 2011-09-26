/* Radio.js*/

zkext.ui.form.Radio= zk.$extends(zkext.ui.form.CheckBox,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
		this.createExt('Ext.form.field.Radio');					 	
	} 	
});