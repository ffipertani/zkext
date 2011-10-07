/* CreateButton.js*/

zkext.ui.button.CreateButton = zk.$extends(zkext.ui.button.Button,{	 
	$define: {	 
		 
	},
	configure_:function(){
		this.$supers('configure_',arguments);
		var wgt = this;
		
		this.getInitialConfig().handler = function(){		 
			var par = wgt.parent;
			while(par){
				if(zkext.ui.grid.Grid.isInstance(par)){
					par.createNewRow();
					break;
				}
				par = par.parent;
			}										     
		}					
	} 
});