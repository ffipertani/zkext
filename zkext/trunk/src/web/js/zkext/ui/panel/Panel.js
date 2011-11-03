/* Panel.js*/

zkext.ui.panel.Panel = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		title:function(val){
			this.setProperty("title","setTitle",val);
		},
		closable:function(val){
			this.setProperty("closable",null,val);
		},
		collapsible:function(val){
			this.setProperty("collapsible",null,val);
		}
	},
	
	getChildren:function(){
		var childs = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {	
			var add=true;
			if(zkext.ui.container.Container.isInstance(w) && w.getDock()!=null){
				add=false;
			}		
			if(w.ext_ == null){				
				add=false;
			}
			if(add){
				childs.push(w.ext_);
			}
		}
		return childs;
	},
	
	getDockedItems:function(){
		var childs = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {	
			if(zkext.ui.container.Container.isInstance(w) && w.getDock()!=null){
				if(w.ext_!=null){				
					childs.push(w.ext_);
				}
			}			
		}
		return childs;
	},	
	
	configure_:function(){
		this.$supers('configure_');	
		var wgt = this;
		var config = this.getInitialConfig();
		var childs = this.getDockedItems();
		
		
		
		if(childs.length>0){
			config.dockedItems = childs;
		}
		
		
		config.listeners = {
				beforeclose:function(panel,opts){	   
		    		if(!wgt.canClose){
		    			wgt.fire("onClose");		    		
		    			return false;
		    		}else{
		    			wgt.canClose = false;
		    		}							    				    
		    	}
		};
	 
	},
	
	collapse:function(){
		if(this.ext_!=null){
			this.ext_.collapse();
		}
	},

	canClose:false,
	close:function(){
		if(this.ext_!=null){
			this.canClose = true;
			this.ext_.close();			
		}
	},
	
	createExt_:function(){		
		this.newInstance('Ext.panel.Panel');			
	} 
	 
});