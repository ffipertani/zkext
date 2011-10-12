/* CheckBox.js*/

zkext.ui.form.CheckBox = zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		 value:function(val){
			 this.setProperty("inputValue",null,val);
		 }
	},
	
	configure_:function(){
		this.$supers('configure_');				
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Checkbox');
	}
});