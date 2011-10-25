/* ComboBox.js*/

zkext.ui.form.ComboBox = zk.$extends(zkext.ui.form.Picker,{	 
	$define: {	 
		 itemLabel:function(val){
			 this.setProperty("displayField",null,val);
		 },
		 itemValue:function(val){
			 this.setProperty("valueField",null,val);
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
		this.store = Ext.create('Ext.data.Store', {
			fields: [this.getItemLabel(), this.getItemValue()],
			//fields:["id","name"],
		    data : [
		        {"id":1, "name":"Maschio"},
		        {"id":2, "name":"Femmina"}
		    ]
		});
		return this.store;
	},
	
	createExt_:function(){
		this.newInstance('Ext.form.field.ComboBox');
	} 	
});