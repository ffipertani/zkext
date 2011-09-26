/* Component.js*/

zkext.ui.Component = zk.$extends(zul.Widget,{	 
	$define: {	 
		initialConfig:function(val){
			
		},
		border:function(val){
				
		},
		cls:function(val){
			
		},
		disabled:function(val){
			
		},
		disabledCls:function(val){
			
		},
		draggable:function(val){
			
		},
		height:function(val){			
			//this.ext_.setHeight(val);		 
			this.setProperty("height","setHeight",val);
		},
		hidden:function(val){
			
		},
		hideMode:function(val){
			
		},
		margin:function(val){
			
		},
		maxHeight:function(val){
			
		},
		maxWidth:function(val){
			
		},
		minHeight:function(val){
			
		},
		minWidth:function(val){
			
		},
		overCls:function(val){
			
		},
		padding:function(val){
			
		},
		style:function(val){
			
		},
		width:function(val){			
			this.setProperty("width","setWidth",val);
			//this.ext_.setWidth(val);			
		},
		region:function(val){
			//alert(val);
			this.setProperty("region",null,val);
		},
		styleWidth:function(val){			
			this.setProperty("width","setWidth",val);		
		},
		styleHeight:function(val){			
			this.setProperty("height","setHeight",val);		
		}
	},
	ext_:null,	 	 
	configure_:function(){	
		var config= this.getInitialConfig();
		if(config==undefined){
			this.setInitialConfig(new Object());
		}
		/*
		this.config_ = new Object();
		
		this.config_.border = this._border;
		this.config_.cls = this._cls;
		this.config_.disabled = this._disabled;
		this.config_.disabledCls = this._disabledCls;
		this.config_.draggable = this._draggable;
		this.config_.height = this._height;
		this.config_.hidden = this._hidden;
	//	this.config_.hideMode = this._hideMode;
		this.config_.margin = this._margin;
		this.config_.maxHeight = this._maxHeight;
		this.config_.maxWidth = this._maxWidth;
		this.config_.minHeight = this._minHeight;
		this.config_.minWidth = this._minWidth;
		this.config_.overCls = this._overCls;
		this.config_.padding = this._padding;
		this.config_.style = this._style;
		this.config_.width = this._width;	
		*/
		 
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
			childs.push(w.ext_);		
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