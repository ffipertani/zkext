/* Row.js*/

zkext.ui.grid.Row = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		 index:function(val){
			 
		 }
	},	
	configure_:function(){	
		this.$supers('configure_',arguments);		
		 
	},
	toObject:function(){
		var obj = new Object();						
		for (var cell = this.firstChild;cell;cell=cell.nextSibling) {		
			if(cell instanceof zkext.ui.grid.Cell){				
				var name = cell.getName();
				var value = cell.getValue();
				if(value==undefined){
					value="";
				}
				//name = name.replace(".","__");
				eval("obj."+name+"='"+value+"';");
				//this.toObjectRecursive(obj,name,value);
				 
			}
		}						
		return obj;
	},
	toObjectRecursive:function(object,name,value){		
		var names = name.split(".");
		if(names.length>1){
			var property = names[0];
			var child;
			if(eval("object."+property+"==null")){
				child = new Object();
			}else{
				eval("child=object."+property+";");
			}
			
			
			this.toObjectRecursive(child,name.substring(name.indexOf(".")+1),value);			
		}else{
			eval("object."+name+"='"+value+"';");			
		}		
	}
	 
});