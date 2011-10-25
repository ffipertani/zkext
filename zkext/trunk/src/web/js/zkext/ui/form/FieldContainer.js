/* FieldContainer.js*/

zkext.ui.form.FieldContainer = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		 label:function(val){
			 this.setProperty("fieldLabel","setLabel",val);
		 }
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