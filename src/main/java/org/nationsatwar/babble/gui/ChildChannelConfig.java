package org.nationsatwar.babble.gui;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

import org.nationsatwar.babble.configuration.ConfigurationHandler;

public class ChildChannelConfig extends CategoryEntry {

	public ChildChannelConfig(GuiConfig owningScreen,
			GuiConfigEntries owningEntryList, IConfigElement configElement) {
		
		super(owningScreen, owningEntryList, configElement);
	}
	
	@Override
	protected GuiScreen buildChildScreen() {
		
		this.btnDefault.visible = false;
		this.btnUndoChanges.visible = false;
		this.toolTip.clear();
		
		String channelName = configElement.getLanguageKey();
		
		List<IConfigElement> list = new ConfigElement(ConfigurationHandler.configuration.getCategory(channelName.toLowerCase())).getChildElements();
		return new GuiConfig(this.owningScreen, list, this.owningScreen.modID, this.owningScreen.configID, false, false, channelName);
	}
}