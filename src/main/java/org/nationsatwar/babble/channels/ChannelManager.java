package org.nationsatwar.babble.channels;

import java.util.ArrayList;
import java.util.List;

public class ChannelManager {
	
	// The almighty channel list
	private static List<ChannelObject> channelList = new ArrayList<ChannelObject>();
	
	// Default channel if exists, otherwise first channel in list, otherwise null
	private static final String defaultChannelName = "Local";
	
	// Default local range if one can't be loaded from config
	private static float localRange = 100;
	
	public static float getLocalRange() {
		
		return localRange;
	}

	public static void setLocalRange(float localRange) {
		
		ChannelManager.localRange = localRange;
	}

	public static void clearChannelList() {
		
		channelList.clear();
	}
	
	public static void addChannel(ChannelObject channel) {
		
		channelList.add(channel);
	}
	
	public static void addChannel(String channelName) {
		
		ChannelObject newChannel = new ChannelObject(channelName);
		channelList.add(newChannel);
	}
	
	public static ChannelObject getChannel(String channelName) {
		
		for (ChannelObject channel : channelList)
			if (channel.getChannelName().equalsIgnoreCase(channelName))
				return channel;
		
		return null;
	}
	
	public static ChannelObject getChannel(int channelID) {
		
		return channelList.get(channelID);
	}
	
	public static int getListSize() {
		
		return channelList.size();
	}
	
	public static ChannelObject getDefaultChannel() {
		
		for (ChannelObject channel : channelList)
			if (channel.getChannelName().equals(defaultChannelName))
				return channel;
		
		if (channelList.size() > 1)
			return channelList.get(0);
		
		return null;
	}
	
	public static ChannelObject getNextChannel(ChannelObject currentChannel) {
		
		int channelIndex = channelList.indexOf(currentChannel);
		
		if (channelList.size() <= channelIndex + 1)
			return channelList.get(0);
		else
			return channelList.get(channelIndex + 1);
	}
	
	public static ChannelObject getPreviousChannel(ChannelObject currentChannel) {
		
		int channelIndex = channelList.indexOf(currentChannel);
		
		if (channelIndex <= 0)
			return channelList.get(channelList.size() - 1);
		else
			return channelList.get(channelIndex - 1);
	}
	
	public static List<String> getChannelNames() {
		
		List<String> channelNames = new ArrayList<String>();
		
		for (ChannelObject channel : channelList)
			channelNames.add(channel.getChannelName());
		
		return channelNames;
	}
	
	public static boolean channelExist(String channelName) {
		
		for (String channelInList : getChannelNames())
			if (channelInList.equalsIgnoreCase(channelName))
				return true;
		
		return false;
	}
}