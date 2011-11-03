/* Component.js*/

zkext.ui.Component = zk.$extends(zkext.ui.AbstractComponent,{	 
	$define: {	 		
		border:function(val){
			this.setProperty("border","setBorder",val);	
		},
		showBorder:function(val){
			this.setProperty("border",null,val);
		},
		cls:function(val){
			
		},
		disabled:function(val){
			
		},
		disabledCls:function(val){
			
		},
		draggable:function(val){
			
		},
		height:function(val){			
			//this.ext_.setHeight(val);		 
			this.setProperty("height","setHeight",val);
		},
		hidden:function(val){
			
		},
		hideMode:function(val){
			
		},
		margin:function(val){
			
		},
		maxHeight:function(val){
			
		},
		maxWidth:function(val){
			
		},
		minHeight:function(val){
			
		},
		minWidth:function(val){
			
		},
		overCls:function(val){
			
		},
		padding:function(val){
			
		},
		style:function(val){
			
		},
		width:function(val){			
			this.setProperty("width","setWidth",val);
			//this.ext_.setWidth(val);			
		},
		region:function(val){
			//alert(val);
			this.setProperty("region",null,val);
		},
		styleWidth:function(val){			
			this.setProperty("width","setWidth",val);		
		},
		styleHeight:function(val){			
			this.setProperty("height","setHeight",val);		
		},
		flex:function(val){
			this.setProperty("flex",null,val)
		},
		anchor:function(val){
			this.setProperty("anchor",null,val);
		},
		columnWidth:function(val){
			this.setProperty("columnWidth",null,val);
		}
	},
	
	
	show:function(){
		this.ext_.show();
	}
	
});