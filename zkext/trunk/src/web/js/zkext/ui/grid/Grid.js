/* Grid.js*/

zkext.ui.grid.Grid = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		 commit:function(val){
			 for(var i=0;i<this.toCommit.length;i++){
				 this.toCommit[i].commit();
			 }
			 toCommit = new Array();
		 },
		 store:function(val){					 
		 },
		 storeId:function(val){			 			
		 },
		 rowEditing:function(val){
			
		 },
		 selectedIndex:function(val){
			 this._selectRow(val);
		 }
	},	 
	//Per evitare che avvenga un onSelectionChange dopo una cancellazione
	justDeleted:false,
	
	toCommit:new Array(),
	
	rowEditingPlugin:null,

	createExt_:function(name){			 
		this.newInstance("Ext.grid.Panel");		
		
		/*
		if(this.getStoreId!=null){
			var app = this.getApplication();
			this._searchStore(this.getStoreId(),app);  	
		}
		*/
		 
	},
		
	configure_:function(){	
		this.$supers('configure_',arguments);
		var wgt = this;
		var plugins = new Array();
		var config = this.getInitialConfig();
		var columns = this.getColumns();
		
		if(columns.length>0){
			config.columns = columns;
		}
		 
		/*	Per cambiare il testo dei bottoni...
		Ext.grid.RowEditor.prototype.cancelBtnText = "My cancel button text";
		Ext.grid.RowEditor.prototype.saveBtnText = "My update button text";
		*/
		
		if(this.getRowEditing()){
			 this.rowEditingPlugin = Ext.create('Ext.grid.plugin.RowEditing');

			 this.rowEditingPlugin.on({
				scope:wgt,
				edit:function(roweditor,changes,record,rowIndex){
					var data = roweditor.record.data;
					var obj = this.createObject(data);
					wgt.fire("onSave",obj);
					this.toCommit.push( roweditor.record);
					//	roweditor.record.commit();
					 				
				},
				update:function(store,record,operation,eOpts){
		    		console.log("Firing onSave event...");
		    	//	if(operation!='commit'){
//		    			wgt.fire("onSave",record.data);
//			    		record.commit();		
//		    		}			    		
		    	},
	            write: function(store, operation){
	            	alert("WRTITE");
	                var record = operation.getRecords()[0],
	                    name = Ext.String.capitalize(operation.action),
	                    verb;
	                    
	                    
	                if (name == 'Destroy') {
	                    record = operation.records[0];
	                    verb = 'Destroyed';
	                } else {
	                    verb = name + 'd';
	                }
	                Ext.example.msg(name, Ext.String.format("{0} user: {1}", verb, record.getId()));
	                
	            }
			 });
			 plugins.push(this.rowEditingPlugin);
		}
		
		config.scroll = false;
		config.autoScroll = false;
		
		 
		config.store = this.createStore();
		//config.store = this.getExtStore();
		config.plugins = plugins;
		config.listeners= {
			
	    	selectionchange:function(model,selected,opts){	    		
	    	 
	    		var o = new Object();
	    		
	    		if (wgt.ext_.getSelectionModel().hasSelection()){
	    			o.id = wgt.getExtStore().indexOf(selected[0]);	
	    		}
	    		
	    		if(o.id == null){
	    			if(this.forceOnSelectionChange){
	    				this.forceOnSelectionChange = false;	
	    			}else{
	    				this._selectedIndex = null;
	    				return;
	    			}
	    		}
	    		
	    		wgt.fire("onSelectionChange",o);
	    	},
	    	afterrender:function(component,eOpts){
	    		wgt._refreshOverflow();	    			    		
			}	   
		};
		
		/* QUESTO DISABILITA I PULSANTI QUANDO NON E' SELEZIONATO NIENTE 
		grid.getSelectionModel().on('selectionchange', function(selModel, selections){
	        grid.down('#delete').setDisabled(selections.length === 0);
	    });
		*/
	},	
	
	createObject:function(data){
		var obj = new Object();
		for (var key in data) {
			if(key.indexOf(ESCAPE_FIELD) != -1){
				var keys = key.split(ESCAPE_FIELD);
				var curObj = obj;
				for(var i=0;i<keys.length-1;i++){
					var curKey = keys[i];
					eval("var child = curObj."+curKey+";");
					if(child == undefined ){
						eval("curObj."+curKey+"=new Object();");
						eval("curObj = curObj."+curKey+";");
					}
				}	
				eval("curObj."+keys[keys.length-1]+"=data[key];")
			}else{
				obj[key] = data[key];
			}
		}
		return obj;
	},
	
	createStore:function(){
		var store = Ext.create('Ext.data.Store', {
		    //model: 'User',
			fields: this.getFields(),
			data:this.getRows(),
		    proxy : {
			        type: 'memory',
			        reader: {
			            type: 'json'
			       //     root:extjs 'users'
			        }
			    }
		});
		return store;
	},
	
	onChildRemoved_:function(wgt){
		this.$supers('onChildRemoved_',arguments);
		if(this.getExtStore()!=null){			
			this.getExtStore().removeAll();
		}
	},
	
	onChildAdded_:function(wgt){
		this.$supers('onChildAdded_',arguments);
		if(this.ext_==null){
			return;
		}
		if(zkext.ui.grid.Rows.isInstance(wgt)){
			this.getExtStore().removeAll();
			try{
				 //Da errore e non si è capito bene il perchè...
				this.getExtStore().add(this.getRows());			
				this._refreshOverflow();				
			}catch(err){}
		}
		
	},
			
	_refreshOverflow:function(){
	
		if(this.getAutoScroll()){
			var dom = this.ext_.getEl().down(".x-grid-view",true);
			dom.style.overflow="auto";
			this.ext_.view.refresh(true);
		}
	},
		
	getFields:function(){
		 return this.getColumnNames();
	},
	 
	getExtStore:function(){
		if(this.ext_ ==null || this.ext_.store==null){
			return null;
		}
		return this.ext_.store;
	},	
		
	getColumns:function(){
		var columns = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.grid.Columns.isInstance(w)){
				for (var wc = w.firstChild;wc;wc=wc.nextSibling) {		
					if(zkext.ui.grid.Column.isInstance(wc)){
						if(wc.getVisible()){
							columns.push(wc.ext_);
						}
					}
				}						
			}
		}	
		return columns;
	},
	
	getColumnNames:function(){
		var columns = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.grid.Columns.isInstance(w)){
				for (var wc = w.firstChild;wc;wc=wc.nextSibling) {		
					if(zkext.ui.grid.Column.isInstance(wc)){
						columns.push(wc.getName());
					}
				}						
			}
		}	
		return columns;
	},
	
	getRows:function(){
		var rows = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.grid.Rows.isInstance(w)){
				for (var wc = w.firstChild;wc;wc=wc.nextSibling) {		
					if(zkext.ui.grid.Row.isInstance(wc)){	
						var obj = wc.toObject();					 
						rows.push(obj);
					}
				}					
			}
		}	
		return rows;
	},
	
	getColumnModel:function(){
		var obj = new Object();
		var columns = this.getColumns();
		for(var i=0;i<columns.length;i++){
			eval("obj."+columns[i].dataIndex+"=null");			
		}
		return obj;
	},
	
	search:function(){
		this.fire("onSearch");
	},
	
	createNewRow:function(){
		var obj = this.getColumnModel();		 		 
		this.getExtStore().insert(0,obj);

		if(this.getRowEditing()){
			this.rowEditingPlugin.startEdit(0, 0);
		}
		
	},
	
	deleteSelectedRow:function(){
		var selection = this.ext_.getView().getSelectionModel().getSelection()[0];
        if (selection) {
            this.getExtStore().remove(selection);
            this.fire("onDelete",selection);
        }
	},
	selectRow:function(index){
		this._doSelectRow(index, false);
	},	 
	
	_addRow:function(row){
		if(this.getExtStore()!=null){			
			var obj = row.toObject();
			var index = row.getIndex();
			this.getExtStore().insert(index,obj);				
		}		
	},	 
	
	_deleteRow:function(index){
		console.log("deleting " + index +" element");
		this.getExtStore().removeAt(index);
		console.log("...deleted");
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
	},
	
	_searchStore:function(id,comp){
		for (var w = comp.firstChild;w;w=w.nextSibling) {	
			 if(w.uuid==id){
				 this.setStore(w);
				 break;
			 }
			 this._searchStore(id,w);
		 }		
	}
});