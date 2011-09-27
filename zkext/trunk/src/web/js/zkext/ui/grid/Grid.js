/* Grid.js*/

zkext.ui.grid.Grid = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		 store:function(val){
			 this.setProperty("store",null,val);
		 } 
	},	 
	configure_:function(){	
		this.$supers('configure_',arguments);		
	
		var config = this.getInitialConfig();
		var columns = this.getColumns();
		if(columns.length>0){
			config.columns = columns;
		}
		
		
	},	
	doSearch:function(){
		this.fire("onSearch");
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
		var rows = this.getRows();
		var store = Ext.create('Ext.data.Store', {		  
			   // fields:['firstname', 'lastname', 'senority', 'dep', 'hired'],
				fields:['firstname', 'senority'],
			    data:rows
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
	}
	 
});