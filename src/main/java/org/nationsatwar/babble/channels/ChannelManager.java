package org.nationsatwar.babble.channels;

import java.util.ArrayList;
import java.util.List;

public class ChannelManager {
	
	public static List<ChannelObject> channelList = new ArrayList<ChannelObject>();
	
	public static void addChannel(String channelName) {
		
		ChannelObject newChannel = new ChannelObject(channelName);
		channelList.add(newChannel);
	}
}