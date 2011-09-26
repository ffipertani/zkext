/* Field.js*/

zkext.ui.form.Field = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		name:function(val){
			this.setProperty("name",null,val);
		},
		value:function(val){
			this.setProperty("value","setValue",val);
		} 
	},
	
	configure_:function(){
		this.$supers('configure_');		
		 					 	
	} 	
});