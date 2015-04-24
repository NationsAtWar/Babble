package org.nationsatwar.babble.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.channels.ChannelObject;

import com.mojang.realmsclient.gui.ChatFormatting;

public class ConfigurationHandler {
	
	public static Configuration configuration;
	
	private static final String nameProperty = "channelName";
	private static final String colorProperty = "channelColor";
	private static final String localProperty = "isLocal";
	private static final String globalProperty = "isGlobal";
	private static final String worldOnlyProperty = "worldOnly";
	private static final String opOnlyProperty = "opOnly";
	
	public static void loadConfig(File configFile) {
		
		// If this is the first time the file is being created, load default channels
		if (!configFile.exists()) {
			
			configuration = new Configuration(configFile);
			loadDefaultConfig();
		}
		
		configuration = new Configuration(configFile);
		
		// Re-populate ChannelManager
		ChannelManager.clearChannelList();
		
		for (String categoryName : configuration.getCategoryNames()) {
			
			if (categoryName.equals("settings"))
				continue; // TODO: Make settings - Reserve this category name
			
			String channelName = configuration.getString(nameProperty, categoryName, "", "");
			
			ChannelObject channel = new ChannelObject(channelName);
			channel.setChannelColor(ChatFormatting.getByName(configuration.getString(colorProperty, categoryName, "", "")));
			channel.setLocal(configuration.getBoolean(localProperty, categoryName, false, ""));
			channel.setWorldOnly(configuration.getBoolean(worldOnlyProperty, categoryName, false, ""));
			channel.setGlobal(configuration.getBoolean(globalProperty, categoryName, false, ""));
			channel.setOpOnly(configuration.getBoolean(opOnlyProperty, categoryName, false, ""));
			
			ChannelManager.addChannel(channel);
		}
	}
	
	public static List<String> getChannelNames() {
		
		List<String> channelNames = new ArrayList<String>();
		
		for (String categoryName : configuration.getCategoryNames()) {
			
			if (!categoryName.equals("settings"))
				channelNames.add(configuration.getString(nameProperty, categoryName, "", ""));
		}
		
		return channelNames;
	}
	
	public static void saveConfig() {
		
		configuration.save();
		
		loadConfig(configuration.getConfigFile());
	}
	
	private static void loadDefaultConfig() {
		
		// Load the configuration file
		configuration.load();
		
		// Create default Local channel
		ChannelObject localChannel = new ChannelObject("Local");
		localChannel.setChannelColor(ChatFormatting.WHITE);
		localChannel.setLocal(true);
		loadChannel(localChannel);
		
		// Create default World channel
		ChannelObject worldChannel = new ChannelObject("World");
		worldChannel.setChannelColor(ChatFormatting.AQUA);
		worldChannel.setWorldOnly(true);
		loadChannel(worldChannel);
		
		// Create default Global channel
		ChannelObject globalChannel = new ChannelObject("Global");
		globalChannel.setChannelColor(ChatFormatting.GREEN);
		globalChannel.setGlobal(true);
		loadChannel(globalChannel);
		
		// Create default Op channel
		ChannelObject opChannel = new ChannelObject("Op");
		opChannel.setChannelColor(ChatFormatting.DARK_PURPLE);
		opChannel.setOpOnly(true);
		loadChannel(opChannel);
		
		// Save the configuration file
		configuration.save();
	}
	
	private static void loadChannel(ChannelObject channel) {

		try {
			
			configuration.get(channel.getChannelName(), nameProperty, channel.getChannelName());
			configuration.get(channel.getChannelName(), colorProperty, channel.getChannelColor().getName(), 
					"The color of the channel name when displayed.");
			configuration.get(channel.getChannelName(), localProperty, channel.isLocal(), 
					"Listener Group: Only people within' a certain radius of the speaker can listen.");
			configuration.get(channel.getChannelName(), globalProperty, channel.isGlobal(), 
					"Listener Group: Everyone can listen.");
			configuration.get(channel.getChannelName(), worldOnlyProperty, channel.isWorldOnly(), 
					"Listener Group: Only people in the same world as speaker can listen.");
			configuration.get(channel.getChannelName(), opOnlyProperty, channel.isOpOnly(), 
					"Listener Group: Only server ops can listen.");
			
		} catch(Exception e) {
			System.out.println("ERROR ERROR! " + e.getMessage());
		}
	}
}