/* AbstractComponent.js*/

zkext.ui.AbstractComponent = zk.$extends(zul.Widget,{	 
	$define: {	 
		initialConfig:function(val){
			
		}
	},
	ext_:null,	 	 
	configure_:function(){	
		var config= this.getInitialConfig();
		if(config==undefined){
			this.setInitialConfig(new Object());
		}		 
	},
	bind_: function () {		
		this.$supers('bind_', arguments);	
		this.configure_();		
	},
	
	unbind_: function () {				
		this.ext_.destroy();
	},
	createExt:function(name){		
		var config = this.getInitialConfig();
		var childs = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {	
			if(w.ext_!=null){
				childs.push(w.ext_);
			}
		}	
		if(childs.length>0){
			config.items = childs;
		}
		 
	
		
		this.ext_ = Ext.create(name,config);
		 
		//this.addChildren();
		 
	},
	addChildren:function(){
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			this.ext_.add(w.ext_);		
		}	 
	},
	setProperty:function(property,functionName){		
		if(this.ext_!=undefined){
			if(functionName==null){
				return;
			}
			var toCall = "this.ext_."+functionName+"(";
			for(var i=2;i<arguments.length;i++){
				toCall = toCall+arguments[i];
				if(i+1<arguments.length){
					toCall = toCall+",";
				}
			}
			toCall = toCall+");";
			eval(toCall);			
		}else{		
			if(this.getInitialConfig() == null){
				this.setInitialConfig(new Object());
			}
			var toEval = "this.getInitialConfig()."+property+"=arguments[2]";
			eval(toEval);			
		}
	},
	redraw:function(out){
		
	}
});