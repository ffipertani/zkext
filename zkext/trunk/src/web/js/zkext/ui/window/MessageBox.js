/* MessageBox.js*/

zkext.ui.window.MessageBox = zk.$extends(zkext.ui.window.Window,{	 
	$define: {	 
		 title:function(val){
			 
		 },
		 message:function(val){
			 
		 },
		 buttons:function(val){
			 
		 },
		 icon:function(val){
			 
		 },
		 showAs:function(val){
			 //this._mustShow = null;
			 //this.show();
			 this.show(val);
		 }
	},
		 		 
	createExt_:function(){		
		
		/*Ext.Msg.show({
		     title:'Save Changes?',
		     msg: 'You are closing a tab that has unsaved changes. Would you like to save your changes?',
		     buttons: Ext.Msg.YESNOCANCEL,
		     icon: Ext.Msg.QUESTION
		});
		*/
		
	},
	
	show:function(o){
		if(o.buttons!=null){
			o.buttons = eval(o.buttons);
		}
		if(o.icon!=null){
			o.icon = eval(o.icon);
		}
		Ext.Msg.show(o);
	}
	
	
	 
	
	
	
	 
	 
	 
});