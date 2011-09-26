/* Grid.js*/

zkext.ui.grid.Grid = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		 store:function(val){
			 this.setProperty("store",null,val);
		 },
		
	},	 
	configure_:function(){	
		this.$supers('configure_',arguments);		
		this.createExt("Ext.grid.Panel");
	},	
	refreshRows:function(row){
		if(this.getStore()!=null){			
			this.getStore().add(row.toObject());
		}		
	},
	createExt:function(name){		
		var config = this.getInitialConfig();
		var columns = this.getColumns();
		if(columns.length>0){
			config.columns = columns;
		}
		
		var rows = this.getRows();
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
		
		
		var store = Ext.create('Ext.data.Store', {		  
		   // fields:['firstname', 'lastname', 'senority', 'dep', 'hired'],
			fields:['firstname', 'senority'],
		    data:rows
		});
		/* */
		this.setStore(store);
		
		this.ext_ = Ext.create(name,config);		
				
	},
	getColumns:function(){
		var columns = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			if(w instanceof zkext.ui.grid.Columns){
				for (var wc = w.firstChild;wc;wc=wc.nextSibling) {		
					if(wc instanceof zkext.ui.grid.Column){
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
			if(w instanceof zkext.ui.grid.Rows){
				for (var wc = w.firstChild;wc;wc=wc.nextSibling) {		
					if(wc instanceof zkext.ui.grid.Row){						 	
						rows.push(wc.toObject());
					}
				}					
			}
		}	
		return rows;
	}
	 
});