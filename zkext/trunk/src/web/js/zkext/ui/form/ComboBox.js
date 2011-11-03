/* ComboBox.js*/

zkext.ui.form.ComboBox = zk.$extends(zkext.ui.form.Picker,{	 
	$define: {	 
		 itemLabel:function(val){
			 this.setProperty("displayField",null,val);
		 },
		 itemValue:function(val){
			 this.setProperty("valueField",null,val);
		 },
		 data:function(val){
			 
		 },
		 value:function(val){			
			 this.setProperty("value","setValue",val);
		 }
	},
	
	store:null,
	
	configure_:function(){
		this.$supers('configure_');		
		var config = this.getInitialConfig();
		config.queryMode = "local";
		config.store= this.createStore();						 
	},
	 
	createStore:function(){
		var storeConfig = new Object();
		storeConfig.fields = [this.getItemLabel(), this.getItemValue()];
		if(this.getData()!=null){
			storeConfig.data = this.getData();	
		}
		
		this.store = Ext.create('Ext.data.Store', storeConfig);
		return this.store;
	},
	
	createExt_:function(){
		this.newInstance('Ext.form.field.ComboBox');
	} 	
});