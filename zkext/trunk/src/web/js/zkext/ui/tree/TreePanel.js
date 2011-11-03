/* TreePanel.js*/

zkext.ui.tree.TreePanel = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		 showRoot:function(val){
			 this.setProperty("rootVisible","setRootVisible",val);
		 },
		 selectedIndex:function(val){
			 this._selectRow(val);
		 }
	},
		 	
	configure_:function(){
		this.$supers('configure_');		
		var wgt = this;
		var config = this.getInitialConfig();
		config.store = this.createStore();		
		config.listeners= {
		    	selectionchange:function(model,selected,opts){	    				    	 
		    		var o = new Object();
		    		
		    		if (wgt.ext_.getSelectionModel().hasSelection()){
		    			//o.id = wgt.ext_.store.indexOf(selected[0]);
		    			o.id = selected[0].data.id;
		    		}
		    		 
		    		wgt.fire("onSelectionChange",o);
		    	}
		};
	},
	
	createExt_:function(){				 
		this.newInstance('Ext.tree.Panel');
		
		//Senza questa da errore nel cambio di view
		this.ext_.getView().loadMask = false;		 
	},
	
	createStore:function(){
		var store = Ext.create('Ext.data.TreeStore', {		  
			root: this.getRoot()		
		});
		return store;
	},
	
	getRoot:function(){
		var root;
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.tree.TreeItem.isInstance(w)){
				root = w.toObject();
				break;
			}
		}	
		return root;
	},
	
	_selectRow:function(index){
		this._doSelectRow(index, true);
	},
	
	_doSelectRow:function(index,silent){
		if(this.ext_!=null){
			if(index>0){
				this.ext_.getSelectionModel().select(index,false,silent);	
			}else{
				this.forceOnSelectionChange=true;
				this.ext_.getSelectionModel().deselectAll();
			}		
		}
	}
	 
});