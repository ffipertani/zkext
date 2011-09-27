/* Date.js*/

zkext.ui.form.Date = zk.$extends(zkext.ui.form.Picker,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');				
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Date');
	}
});