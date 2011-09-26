/* HtmlEditor.js*/

zkext.ui.form.HtmlEditor= zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		 
	},
	
	configure_:function(){
		this.$supers('configure_');		
		this.createExt('Ext.form.field.HtmlEditor');					 	
	} 	
});