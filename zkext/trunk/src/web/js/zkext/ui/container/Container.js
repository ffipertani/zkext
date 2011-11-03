/* Container.js*/

zkext.ui.container.Container = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		layout:function(val){				
			this.setProperty("layout",null,val);
		},
		dock:function(val){
			this.setProperty("dock",null,val);
		},
		autoScroll:function(val){
			this.setProperty("autoScroll",null,val);
		}
	},
	
	configure_:function(){	
		this.$supers('configure_',arguments);		
		var config = this.getInitialConfig();
		
		//this.getInitialConfig().suspendLayout = true;
	},
	
	newInstance:function(name){
		this.$supers('newInstance',arguments);
		this.doLayout();		
	},
		 
	onChildAdded_:function(wgt){
		this.$supers('onChildAdded_',arguments);		
		if(this.ext_ != null  &&  wgt.ext_!=null){					
			this.ext_.insert(0,wgt.ext_);
			wgt.parent = this;
		//	this.doLayout();					
		}	
	},
	
	onChildRemoved_:function(wgt){
		this.$supers('onChildRemoved_',arguments);
		if(this.ext_ != null  &&  wgt.ext_!=null){					
			this.ext_.remove(wgt.ext_);
			wgt.ext_.destroy();				
		}
	},
	
	doLayout:function(){
		this.$supers('doLayout',arguments);		
		if(this.ext_!=null){
			this.ext_.doLayout();
		}
		
	}
	 
});