/* Date.js*/

zkext.ui.form.Date = zk.$extends(zkext.ui.form.Picker,{	 
	$define: {	 
		 value:function(val){
			 this.setProperty("value","setValue",val);
		 }
	},
	
	configure_:function(){
		this.$supers('configure_');		
		var config = this.getInitialConfig();
		config.format="d/m/Y";
		config.altFormats="d/m/Y";
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Date');
	}
});