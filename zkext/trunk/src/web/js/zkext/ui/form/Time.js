/* Time.js*/

zkext.ui.form.Time = zk.$extends(zkext.ui.form.Picker,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
							 	
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Time');
	} 	
});