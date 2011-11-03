/* EditButton.js*/

zkext.ui.button.EditButton = zk.$extends(zkext.ui.button.Button,{	 
	$define: {	 
		 
	},
	configure_:function(){
		this.$supers('configure_',arguments);
		var wgt = this;
		
		this.getInitialConfig().handler = function(){		 
			var par = wgt.parent;
			while(par){				
				if(zkext.ui.form.Form.isInstance(par)){
					par.submit();
					break;
				}
				par = par.parent;
			}										     
		}					
	} 
});