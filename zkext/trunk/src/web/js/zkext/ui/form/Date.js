/* Date.js*/

zkext.ui.form.Date = zk.$extends(zkext.ui.form.Picker,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
		this.createExt('Ext.form.field.Date');					 	
	} 	
});