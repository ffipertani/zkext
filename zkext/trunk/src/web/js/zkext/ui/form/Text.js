/* Text.js*/

zkext.ui.form.Text = zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		 allowBlank:function(val){
			 this.setProperty("allowBlank",null,val);
		 }
	},
	
	configure_:function(){	
		this.$supers('configure_');		
		this.createExt('Ext.form.field.Text');					 	
	} 	
});