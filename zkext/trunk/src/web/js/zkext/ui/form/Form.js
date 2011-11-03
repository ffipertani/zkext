/* Form.js*/

zkext.ui.form.Form = zk.$extends(zkext.ui.panel.Panel,{	 
	$define: {	 
		text:function(val){
			this.setProperty("text","setText",val);
		},
		model:function(val){
			this.loadModel(val);
		}
	},
	
	configure_:function(){
		var config = this.initialConfig();
		
	/*
		config.buttons = [{
	        text: 'Reset',
	        handler: function() {
	            this.up('form').getForm().reset();
	        }
	    }, {
	        text: 'Submit',
	        formBind: true, //only enabled once the form is valid
	        disabled: true,
	        handler: function() {
	            var form = this.up('form').getForm();
	            if (form.isValid()) {
	                form.submit({
	                    success: function(form, action) {
	                       Ext.Msg.alert('Success', action.result.msg);
	                    },
	                    failure: function(form, action) {
	                        Ext.Msg.alert('Failed', action.result.msg);
	                    }
	                });
	            }
	        }
	    }];
	    */
		var buttons = this.getButtons();
		if(buttons!=null){
			config.buttons = buttons;
		}
		this.$supers('configure_');				 				
		
	},
	
	getButtons:function(){
		var buttons = new Array();
		for (var w = this.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.form.ButtonBar.isInstance(w)){
				for (var wc = w.firstChild;wc;wc=wc.nextSibling) {		
					if(zkext.ui.button.Button.isInstance(wc)){
						buttons.push(wc.ext_);
					}
				}						
			}
		}	
		return buttons;
	},
	
	createExt_:function(){
		this.newInstance('Ext.form.Panel');
	},
	
	submit:function(){
		var model = new Object();
		this._submitRecursive(this,model);
		this.fire("onEdit",model);
	},
	
	_submitRecursive:function(comp,obj){
		for (var w = comp.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.form.Field.isInstance(w)){
				var id = w.uuid;
				var value = w.getModel();
				obj[id] = value;
			}else{
				this._submitRecursive(w,obj);
			}
		}
	},
	
	save:function(){
		/*
		this.ext_.submit({
            success: function(form, action) {
               Ext.Msg.alert('Success', action.result.msg);
            },
            failure: function(form, action) {
                Ext.Msg.alert('Failed', action.result.msg);
            }
        });
        */		
		
		var model = new Object();
		this._saveRecursive(this, model);
		//var model = this.ext_.getValues();
		this.fire("onSave",model);
	},
	
	_saveRecursive:function(comp,obj){		
		for (var w = comp.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.form.Field.isInstance(w)){
				var key = w.getName();
				var value = w.getModel();		
				if(key == null){
					continue;
				}
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
					eval("curObj."+keys[keys.length-1]+"=value;")
				}else{
					obj[key] = value;
				}
				
				
				
				
			//	var toEval = "obj."+name+"='"+value+"';";
			//	eval(toEval);
			}else{
				this._saveRecursive(w,obj);
			}
		}
	},
	
	reset:function(){
		this.ext_.getForm().reset();
	},
	
	loadModel:function(model){
		this.ext_.loadRecord(model);
	}
});