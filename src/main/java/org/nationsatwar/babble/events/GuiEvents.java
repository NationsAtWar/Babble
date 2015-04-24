package org.nationsatwar.babble.events;

import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.client.GuiIngameModOptions;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.nationsatwar.babble.gui.ChatConfig;

public class GuiEvents {
	
	@SubscribeEvent
	public void openModOptionsEvent(GuiOpenEvent event) {
		
		if (event.gui instanceof GuiIngameModOptions)
			event.gui = new ChatConfig(null);
	}
}