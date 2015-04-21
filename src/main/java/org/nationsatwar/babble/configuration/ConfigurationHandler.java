package org.nationsatwar.babble.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
	
	private static Configuration configuration;
	
	public static void reloadConfig(File configFile) {
		
		// Create the configuration object from the given configuration file
		configuration = new Configuration(configFile);
		
		try {
			// Load the configuration file
			configuration.load();
			
			// Set Channel Defaults
			// Set Channel Local
			configuration.get("Local", "channelName", "Local");
			configuration.get("Local", "isLocal", true);
			configuration.get("Local", "worldOnly", true);
		} catch(Exception e) {
			System.out.println("ERROR ERROR! " + e.getMessage());
		} finally {
			// Save the configuration file
			configuration.save();
		}
	}
}