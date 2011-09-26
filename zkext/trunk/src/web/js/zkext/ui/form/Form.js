/* Form.js*/

zkext.ui.form.Form = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		text:function(val){
			this.setProperty("text","setText",val);
		}
	},
	
	configure_:function(){
		this.$supers('configure_');		
		 			
		this.createExt('Ext.form.Panel');
		
	} 	
});