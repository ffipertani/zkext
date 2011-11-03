/* CheckBox.js*/

zkext.ui.form.CheckBox = zk.$extends(zkext.ui.form.Field,{	 
	$define: {	 
		checked:function(val){			
			 this.setProperty("checked","setValue",val);
		},
		value:function(val){
			 this.setProperty("inputValue",null,val);
			/*
			 if(this.ext_!=null){
				 alert(this.getLabel() + "    " + val);
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
		this.newInstance('Ext.form.field.Checkbox');
	}
	
	 
});