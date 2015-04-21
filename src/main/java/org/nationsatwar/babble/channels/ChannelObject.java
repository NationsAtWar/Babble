package org.nationsatwar.babble.channels;

public class ChannelObject {

	private String channelName;
	
	private boolean isLocal;
	private boolean worldOnly;

	public ChannelObject(String channelName) {
		
		this.channelName = channelName;
	}
	
	public String getChannelName() {
		return channelName;
	}

	public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}

	public boolean isWorldOnly() {
		return worldOnly;
	}

	public void setWorldOnly(boolean worldOnly) {
		this.worldOnly = worldOnly;
	}
}