/* Field.js*/

zkext.ui.form.Field = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		name:function(val){
			this.setProperty("name",null,val);
		},
		value:function(val){
			this.setProperty("value","setValue",val+"");
		},
		label:function(val){
			this.setProperty("fieldLabel","setLabel",val);
			//this.setProperty("boxLabel","setLabel",val);
		},
		boxLabel:function(val){
			this.setProperty("boxLabel","setBoxLabel",val);
		}		
	}
});