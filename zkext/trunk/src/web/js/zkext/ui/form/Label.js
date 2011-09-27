/* Label.js*/

zkext.ui.form.Label = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		text:function(val){
			this.setProperty("text","setText",val);
		}
	},
	
	configure_:function(){
		this.$supers('configure_');		
		 		
	 
	},
	createExt_:function(){
		this.newInstance('Ext.form.Label');
	} 	
});