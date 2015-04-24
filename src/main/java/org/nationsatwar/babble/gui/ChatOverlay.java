package org.nationsatwar.babble.gui;

import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.channels.ChannelObject;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatOverlay extends Gui {
	
	private Minecraft mc;
	private boolean channelSet = false;
	
	public ChatOverlay(Minecraft mc) {
		
		super();
		
		// We need this to invoke the render engine.
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void eventHandler(RenderGameOverlayEvent event) {
		
		// Set player's channel if none is already assigned
		if (!channelSet) {
			
			channelSet = true;
			
			NBTTagCompound playerData = mc.thePlayer.getEntityData();
			
			if (!playerData.hasKey("Channel")) {
				
				ChannelObject defaultChannel = ChannelManager.getDefaultChannel();
				
				if (defaultChannel == null)
					return;
				
				playerData.setString("Channel", defaultChannel.getChannelName());
			}
		}
		
		String channelName = mc.thePlayer.getEntityData().getString("Channel");
		ChannelObject channel = ChannelManager.getChannel(channelName);
		
		// Displays active channel name above chat bar
		if (mc.currentScreen != null && mc.currentScreen.getClass() == GuiChat.class)
			drawString(mc.fontRendererObj, channel.getChannelColor() + "(" + channelName + ")", 2, mc.currentScreen.height - 25, 0xEE6688);
	}
}