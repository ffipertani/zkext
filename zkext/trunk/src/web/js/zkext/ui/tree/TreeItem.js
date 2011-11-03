/* TreeItem.js*/

zkext.ui.tree.TreeItem = zk.$extends(zkext.ui.Component,{	 
	$define: {	 
		 expandable:function(val){
			 this.setProperty("expandable","setExplandable",val);
		 }, 
		 expanded:function(val){
			 this.setProperty("expanded","setExpanded",val);
		 },
		 text:function(val){
			 this.setProperty("text","setText",val);
		 },
		 icon:function(val){
			 this.setProperty("icon","setIcon",val);
		 },
		 leaf:function(val){
			 this.setProperty("leaf","setLeaf",val);
		 },
		 itemId:function(val){
			 
		 }
		 
	},
		 	
	configure_:function(){
		this.$supers('configure_');		
		var config = this.getInitialConfig();					 
	},
		 
	toObject:function(){
		return this.toObjectRecursive(this);
	},
	
	toObjectRecursive:function(comp){
		var o = new Object();
		o.expandable = comp.getExpandable();
		o.expanded = comp.getExpanded();
		o.text = comp.getText();
		o.icon = comp.getIcon();
		o.leaf = comp.getLeaf();
		o.id = comp.uuid;
		o.children = new Array();			
		for (var w = comp.firstChild;w;w=w.nextSibling) {		
			if(zkext.ui.tree.TreeItem.isInstance(w)){
				var child = w.toObject();
				o.children.push(child);
			}
		}
		return o;
	}
			
});