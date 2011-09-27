/* ComboBox.js*/

zkext.ui.form.ComboBox = zk.$extends(zkext.ui.form.Picker,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
		this.getInitialConfig().store=new Array();							 
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.ComboBox');
	} 	
});