package org.nationsatwar.babble.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.channels.ChannelObject;

public class ConfigurationHandler {
	
	private static Configuration configuration;

	private static final String nameProperty = "channelName";
	private static final String localProperty = "isLocal";
	private static final String worldOnlyProperty = "worldOnly";
	private static final String opOnlyProperty = "opOnly";
	
	public static void loadConfig(File configFile) {
		
		// If this is the first time the file is being created, load default channels
		if (!configFile.exists()) {

			configuration = new Configuration(configFile);
			loadDefaultConfig();
		}
		
		// Re-populate ChannelManager
		ChannelManager.clearChannelList();
		
		for (String channelName : configuration.getCategoryNames()) {
			
			if (channelName.equals("Settings"))
				continue; // TODO: Make settings - Reserve this category name
			
			ChannelObject channel = new ChannelObject(channelName);
			channel.setLocal(configuration.getBoolean(localProperty, channelName, false, ""));
			channel.setWorldOnly(configuration.getBoolean(worldOnlyProperty, channelName, false, ""));
			channel.setOpOnly(configuration.getBoolean(opOnlyProperty, channelName, false, ""));
			
			ChannelManager.addChannel(channel);
		}
		
	}
	
	private static void loadDefaultConfig() {
		
		// Create default Local channel
		ChannelObject localChannel = new ChannelObject("Local");
		localChannel.setLocal(true);
		localChannel.setWorldOnly(true);
		loadChannel(localChannel);
		
		// Create default Op channel
		ChannelObject opChannel = new ChannelObject("Op");
		localChannel.setOpOnly(true);
		loadChannel(opChannel);
	}
	
	private static void loadChannel(ChannelObject channel) {

		try {
			// Load the configuration file
			configuration.load();
			
			configuration.get(channel.getChannelName(), nameProperty, channel.getChannelName());
			configuration.get(channel.getChannelName(), localProperty, channel.isLocal());
			configuration.get(channel.getChannelName(), worldOnlyProperty, channel.isWorldOnly());
			configuration.get(channel.getChannelName(), opOnlyProperty, channel.isOpOnly());
			
		} catch(Exception e) {
			System.out.println("ERROR ERROR! " + e.getMessage());
		} finally {
			// Save the configuration file
			configuration.save();
		}
	}
}