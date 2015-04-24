package org.nationsatwar.babble.channels;

import java.util.ArrayList;
import java.util.List;

public class ChannelManager {
	
	private static List<ChannelObject> channelList = new ArrayList<ChannelObject>();
	private static final String defaultChannelName = "Local";
	
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
			if (channel.getChannelName().equals(channelName))
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
}