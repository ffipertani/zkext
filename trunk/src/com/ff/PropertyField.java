package com.ff;

import com.ff.enums.UpdateType;

public class PropertyField {
	private UpdateType updateClient = UpdateType.ALWAYS;
	private UpdateType updateServer = UpdateType.NEVER;
	private Class originClass;
	private String name;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UpdateType getUpdateClient() {
		return updateClient;
	}
	public void setUpdateClient(UpdateType updateClient) {
		this.updateClient = updateClient;
	}
	public UpdateType getUpdateServer() {
		return updateServer;
	}
	public void setUpdateServer(UpdateType updateServer) {
		this.updateServer = updateServer;
	}
	public Class getOriginClass() {
		return originClass;
	}
	public void setOriginClass(Class originClass) {
		this.originClass = originClass;
	}	
	
	
}
