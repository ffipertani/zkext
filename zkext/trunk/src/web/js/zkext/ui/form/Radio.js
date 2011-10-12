/* Radio.js*/

zkext.ui.form.Radio= zk.$extends(zkext.ui.form.CheckBox,{	 
	$define: {	 
		 value:function(val){
			 this.setProperty("inputValue",null,val);
		 }
	},
	
	configure_:function(){
		this.$supers('configure_');								 
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Radio');
	} 	
});