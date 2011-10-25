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
		var model = this.ext_.getValues();
		this.fire("onSave",model);
	},
	reset:function(){
		this.ext_.getForm().reset();
	},
	loadModel:function(model){
		this.ext_.loadRecord(model);
	}
});