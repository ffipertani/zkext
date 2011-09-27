/* TextArea.js*/

zkext.ui.form.TextArea = zk.$extends(zkext.ui.form.Text,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
							 
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.TextArea');
	} 	
});