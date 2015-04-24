package org.nationsatwar.babble.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.configuration.ConfigurationHandler;

public class ChatConfig extends GuiConfig {
	
	public ChatConfig(GuiScreen guiScreen) {
		
		super(guiScreen, getConfigElements(), 
				Babble.MODID, false, false, "Babble Options");
		
		this.titleLine2 = ConfigurationHandler.configuration.getConfigFile().getAbsolutePath();
	}
	
	private static List<IConfigElement> getConfigElements() {
		
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		list.addAll(new ConfigElement(ConfigurationHandler.configuration.getCategory("settings")).getChildElements());
		
		for (String channelName : ConfigurationHandler.getChannelNames())
			list.add(new DummyConfigElement.DummyCategoryElement("Edit Channel: " + channelName, channelName, ChildChannelConfig.class));
		
		return list;
	}
}