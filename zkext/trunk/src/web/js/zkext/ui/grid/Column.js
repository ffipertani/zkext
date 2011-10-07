/* Column.js*/

zkext.ui.grid.Column = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		 text:function(val){
			 this.setProperty("text","setText",val);
		 },
		 name:function(val){
			 this.setProperty("dataIndex",null,val);
		 }
	},	
	configure_:function(){	
		this.$supers('configure_',arguments);		
		var config = this.getInitialConfig();
		config.field = new Object();
		config.field.xtype = 'textfield';
	},
	createExt_:function(){
		this.newInstance("Ext.grid.column.Column");
	}
	 
});