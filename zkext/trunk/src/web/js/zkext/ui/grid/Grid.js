/* Grid.js*/

zkext.ui.grid.Grid = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		 store:function(val){
			 this.setProperty("store",null,val);
		 },
		 rowEditing:function(val){
			
		 }
	},	 
	rowEditingPlugin:null,

	configure_:function(){	
		this.$supers('configure_',arguments);
		var wgt = this;
		var plugins = new Array();
		var config = this.getInitialConfig();
		var columns = this.getColumns();
		if(columns.length>0){
			config.columns = columns;
		}
		
		if(this.getRowEditing()){
			 this.rowEditingPlugin = Ext.create('Ext.grid.plugin.RowEditing');
			 this.rowEditingPlugin.on({
				scope:this,
				afteredit:function(roweditor,changes,record,rowIndex){				 	
				// 	roweditor.record.commit();									
				}
			 });
			 plugins.push(this.rowEditingPlugin);
		}
		
		config.plugins = plugins;
		
		/* QUESTO DISABILITA I PULSANTI QUANDO NON E' SELEZIONATO NIENTE 
		grid.getSelectionModel().on('selectionchange', function(selModel, selections){
	        grid.down('#delete').setDisabled(selections.length === 0);
	    });
		*/
	},	
	doSearch:function(){
		this.fire("onSearch");
	},
	createNewRow:function(){
		var obj = this.getColumnModel();		 		 
		this.getStore().insert(0,obj);

		if(this.getRowEditing()){
			this.rowEditingPlugin.startEdit(0, 0);
		}
		
	},
	deleteSelectedRow:function(){
		var selection = this.ext_.getView().getSelectionModel().getSelection()[0];
        if (selection) {
            this.getStore().remove(selection);
            this.fire("onDelete",selection);
        }
	},
	addRow:function(row){
		if(this.getStore()!=null){			
			this.getStore().add(row.toObject());
		}		
	},	 
	onChildRemoved_:function(wgt){
		this.$supers('onChildRemoved_',arguments);
		if(this.getStore()!=null){			
			this.getStore().removeAll();
		}
	},
	onChildAdded_:function(wgt){
		this.$supers('onChildAdded_',arguments);
		if(this.ext_==null){
			return;
		}
		if(zkext.ui.grid.Rows.isInstance(wgt)){
			this.getStore().removeAll();
			try{
				 //Da errore e non si è capito bene il perchè...
				this.getStore().add(this.getRows());
			}catch(err){}
		}
	},
	createExt_:function(name){		
		
		/*
		var store = Ext.create('Ext.data.Store', {		  
		    fields:['firstname', 'lastname', 'senority', 'dep', 'hired'],
		    data:[
		        {firstname:"Michael", lastname:"Scott", senority:7, dep:"Manangement", hired:"01/10/2004"},
		        {firstname:"Dwight", lastname:"Schrute", senority:2, dep:"Sales", hired:"04/01/2004"},
		        {firstname:"Jim", lastname:"Halpert", senority:3, dep:"Sales", hired:"02/22/2006"},
		        {firstname:"Kevin", lastname:"Malone", senority:4, dep:"Accounting", hired:"06/10/2007"},
		        {firstname:"Angela", lastname:"Martin", senority:5, dep:"Accounting", hired:"10/21/2008"}
		    ]
		});
			*/
					
		this.createStore();
		this.newInstance("Ext.grid.Panel");		
				
	},
	createStore:function(){
		var wgt = this;
		var rows = this.getRows();
		var store = Ext.create('Ext.data.Store', {		  
			   // fields:['firstname', 'lastname', 'senority', 'dep', 'hired'],
			//	autoSync:true,
				fields:['firstname', 'senority'],
			    data:rows,
			    proxy: {
			        type: 'memory',
			        reader: {
			            type: 'json'
			       //     root: 'users'
			        }
			    },
			    listeners: {
			    	update:function(store,record,operation,eOpts){
			    		console.log("Firing onSave event...");
			    		if(operation!='commit'){
			    			wgt.fire("onSave",record.data);
				    		record.commit();		
			    		}			    		
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
		        }
			    
			});
			/* */
		this.setStore(store);
	},
	getColumns:function(){
		var columns = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.grid.Columns.isInstance(w)){
				for (var wc = w.firstChild;wc;wc=wc.nextSibling) {		
					if(zkext.ui.grid.Column.isInstance(wc)){
						columns.push(wc.ext_);
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
						rows.push(wc.toObject());
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
	}
	 
});