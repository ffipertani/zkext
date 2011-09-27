/* Panel.js*/

zkext.ui.panel.Panel = zk.$extends(zkext.ui.container.Container,{	 
	$define: {	 
		title:function(val){
			this.setProperty("title","setTitle",val);
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
		var config = this.getInitialConfig();
		var childs = this.getDockedItems();
		if(childs.length>0){
			config.dockedItems = childs;
		}
	 
	},
	createExt_:function(){		
		this.newInstance('Ext.panel.Panel');			
	} 
	 
});