/* Radio.js*/

zkext.ui.form.Radio= zk.$extends(zkext.ui.form.CheckBox,{	 
	$define: {	 
		 value:function(val){
			 this.setProperty("inputValue",null,val);
			 /*
			 if(this.ext_!=null){
				 if(this.getLabel() == val){
					 this.ext_.setValue(true);
				 }else{
					 this.ext_.setValue(false);
				 }
		 	}
		 	*/
		 }
	},
	
	configure_:function(){
		this.$supers('configure_');								 
	},
	createExt_:function(){
		this.newInstance('Ext.form.field.Radio');
	} 	
});