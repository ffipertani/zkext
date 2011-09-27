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
			config = new Object();
			this.setInitialConfig(config);
		}		
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
	doLayout:function(){			
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			w.doLayout();
		}
	},
	getCurrentPage:function(){
		var par = this.parent;
		while(par){
			if(zkext.ui.page.Page.isInstance(par)){
				return par;
			}
			par = par.parent;
		}
		return null;
	},
	redraw:function(out){
		 
	}
});