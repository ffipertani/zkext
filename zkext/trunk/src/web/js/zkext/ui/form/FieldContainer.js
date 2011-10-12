/* FieldContainer.js*/

zkext.ui.form.FieldContainer = zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		 
	},
	configure_:function(){	
		this.$supers('configure_',arguments);		
		var config = this.getInitialConfig();
		
		//this.getInitialConfig().suspendLayout = true;
	},
	createExt_:function(){
		
		//this.newInstance('Ext.form.CheckboxGroup');
		this.newInstance('Ext.form.FieldContainer');
	}
});