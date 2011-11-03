/* AbstractComponent.js*/

zkext.ui.AbstractComponent = zk.$extends(zul.Widget,{	 
	$define: {	 
		initialConfig:function(val){
			
		},
		
		callMethod:function(val){
			_callMethod = null;
			
			var method = val.method;
			var args = val.args;
			var toeval = "this."+method+"(";
			if(args!=null){
				for(var i=0;i<args.length;i++){
					if(i>0){
						toeval += ",";
					}
					toeval += args[i];				
				}
			}
			toeval+=");";
			eval(toeval);
			
			
		}
	},
	
	initialConfig:function(){
		var config= this.getInitialConfig();
		if(config==undefined){
			config = new Object();
			this.setInitialConfig(config);
		} 
		return config;
	},
	
	ext_:null,	
	
	configure_:function(){	
		var config= this.initialConfig();
		var childs = this.getChildren();
		if(childs.length>0){
			config.items = childs;
		}
	},
	
	bind_: function () {		
		this.$supers('bind_', arguments);	
		this.configure_();		
		this.createExt_();
	},
	
	unbind_: function () {		
		if(this.ext_!=null){
			this.ext_.destroy();
		}
	},
	
	getChildren:function(){
		var childs = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {	
			if(w.ext_!=null){
				childs.push(w.ext_);
			}
		}
		return childs;
	},
	
	newInstance:function(name){				
		this.ext_ = Ext.create(name,this.getInitialConfig());
	},
	
	createExt_:function(){				 				 	
		//this.newInstance(name);		 		
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
				if(typeof arguments[i] == 'string'){
					toCall = toCall+"'"+arguments[i]+"'";	
				}else{
					toCall = toCall+arguments[i];
				}
				
				if(i+1<arguments.length){					
					toCall = toCall+",";
				}
			}
			toCall = toCall+");";
			eval(toCall);			
		}else{					
			var toEval = "this.initialConfig()."+property+"=arguments[2]";
			eval(toEval);			
		}
	},
	
	doLayout:function(){			
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			w.doLayout();
		}
	},
	
	getApplication:function(){
		var par = this.parent;
		while(par){
			if(zkext.ui.application.Application.isInstance(par)){
				return par;
			}
			par = par.parent;
		}
		return null;
	},
	
	redraw:function(out){
		 
	}
});