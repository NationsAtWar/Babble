package org.nationsatwar.babble.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.channels.ChannelObject;
import org.nationsatwar.babble.packets.PacketChannel;
import org.nationsatwar.babble.proxy.ClientProxy;

public class KeyEvents {
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		
		if (ClientProxy.chatKey.isPressed()) {
			
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;			
			player.openGui(Babble.instance, 21, player.getEntityWorld(), 0, 0, 0);
		}
		
		if (ClientProxy.cycleLeftKey.isPressed() || ClientProxy.cycleRightKey.isPressed()) {
			
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
			NBTTagCompound playerData = player.getEntityData();
			ChannelObject currentChannel = ChannelManager.getChannel(playerData.getString("Channel"));
			
			String newChannelName = "";
			
			System.out.println("Test: " + newChannelName);
			
			if (ClientProxy.cycleLeftKey.isKeyDown())
				newChannelName = ChannelManager.getPreviousChannel(currentChannel).getChannelName();
			else if (ClientProxy.cycleRightKey.isKeyDown())
				newChannelName = ChannelManager.getNextChannel(currentChannel).getChannelName();
			else {
				
				System.out.println("Could not get new channel name.");
				return;
			}
			
			playerData.setString("Channel", newChannelName);
			
			Babble.channel.sendToServer(new PacketChannel(player.getUniqueID().toString(), newChannelName));
		}
	}
}