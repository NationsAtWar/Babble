package org.nationsatwar.babble.channels;

import com.mojang.realmsclient.gui.ChatFormatting;

public class ChannelObject {

	private String channelName;
	
	// Channel properties
	private ChatFormatting channelColor = ChatFormatting.WHITE;
	
	// Listener Groups
	private boolean isLocal;
	private boolean isGlobal;
	private boolean worldOnly;
	private boolean opOnly;

	public ChannelObject(String channelName) {
		
		this.channelName = channelName;
	}
	
	public ChatFormatting getChannelColor() {
		return channelColor;
	}

	public void setChannelColor(ChatFormatting channelColor) {
		this.channelColor = channelColor;
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

	public boolean isGlobal() {
		return isGlobal;
	}

	public void setGlobal(boolean isGlobal) {
		this.isGlobal = isGlobal;
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
}