/* Form.js*/

zkext.ui.form.Form = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		text:function(val){
			this.setProperty("text","setText",val);
		}
	},
	
	configure_:function(){
		this.$supers('configure_');				 				
		
	},
	createExt_:function(){
		this.newInstance('Ext.form.Panel');
	} 	
});