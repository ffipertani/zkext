/* Store.js*/

zkext.ui.store.Store = zk.$extends(zkext.ui.AbstractComponent,{	 
	$define: {			 
		 modelUsed:function(val){			 
			 this.createModels(val);
		 },
		 addModel:function(val){
			this.addModel(val);
		}
	},
	
	
	configure_:function(){
		this.$supers('configure_');	
		var wgt = this;
		var config = this.getInitialConfig();
		config.autoSync= true;
		config.fields = [];
		//config.model = 'User';//
		//config.model =this._modelUsed[0].name,
		config.proxy = {
	        type: 'memory',
	        reader: {
	            type: 'json'
	       //     root: 'users'
	        }
	    };
 
	 
		
	},
	instance:null,
	createExt_:function(){
	 		
		//this.newInstance('Ext.data.Store');
		
		/*
		var store = Ext.create('Ext.data.Store', {
		    //model: 'User',
			fields: ['id','firstname','senority'],
		    proxy : {
			        type: 'memory',
			        reader: {
			            type: 'json'
			       //     root: 'users'
			        }
			    }
		});
		*/
	//	var store = Ext.create('Ext.data.Store', this.getInitialConfig());
		
		
	//	this.instance = store;
		/*	*/
	},
	addModel:function(val){
		this.instance.add(val);
	},
	removeAll:function(){
		this.instance.add();
	},
	
	createModels:function(models){
		for(var i=0;i<models.length;i++){
			var model = models[i];
			var modelConfig = new Object();			
			modelConfig.extend = 'Ext.data.Model';
			modelConfig.fields = new Array();
			var fields = model.fields;
			for(var k=0;k<fields.length;k++){
				var field = fields[k];
				if(field.type!="entity"){
					var modelField = new Object();
					modelField.name = field.name;
					modelField.type = field.type;
					modelConfig.fields.push(modelField);
				}
			}
		//	Ext.define(model.name, modelConfig);
			
			Ext.define('User', {
			    extend: 'Ext.data.Model',
			    fields: [
			        {name:'id',type:'number'},
			        {name:'firstname',type:'string'},			       
			        {name:'senority',type:'number'},
			        {name:'sex',mapping:'sesso.nome',type:'string'}
			       ]			   
			});
			
			Ext.define('Sesso', {
			    extend: 'Ext.data.Model',
			    fields: [
			        {name:'id',type:'number'},
			        {name:'nome',type:'string'},			       
			        {name:'descrizione',type:'string'}
				],
				belogsTo:'User'
			    
				
			});
			
		}
	}
	 	
});