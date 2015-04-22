package org.nationsatwar.babble.channels;

public class ChannelObject {

	private String channelName;
	
	private boolean isLocal;
	private boolean worldOnly;
	private boolean opOnly;
	private boolean everyoneCanUse;
	private boolean everyoneCanListen;

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

	public boolean isOpOnly() {
		return opOnly;
	}

	public void setOpOnly(boolean opOnly) {
		this.opOnly = opOnly;
	}

	public boolean isEveryoneCanUse() {
		return everyoneCanUse;
	}

	public void setEveryoneCanUse(boolean everyoneCanUse) {
		this.everyoneCanUse = everyoneCanUse;
	}

	public boolean isEveryoneCanListen() {
		return everyoneCanListen;
	}

	public void setEveryoneCanListen(boolean everyoneCanListen) {
		this.everyoneCanListen = everyoneCanListen;
	}
}