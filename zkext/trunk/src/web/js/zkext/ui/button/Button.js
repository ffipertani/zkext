/* Button.js*/

zkext.ui.button.Button = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		text:function(val){		
			this.setProperty("text","setText",val);
		}
	},
	configure_:function(){
		this.$supers('configure_',arguments);
		var wgt = this;
		
		this.getInitialConfig().handler = function(){		 
			wgt.fire("onClick");	    						
		}					
	},
	createExt_:function(){
		this.newInstance('Ext.Button');
	}
});