package org.nationsatwar.babble.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.channels.ChannelObject;
import org.nationsatwar.palette.chat.ChatMessage;
import org.nationsatwar.palette.chat.MessageType;

public class ChatEvents {
	
	@SubscribeEvent
	public void chatMessage(ServerChatEvent event) {
		
		// Formats the message
		String message = ChatMessage.formatMessage(event.username, event.message, MessageType.NORMAL);
		
		// Disables normal functionality
		event.setCanceled(true);
		
		NBTTagCompound playerData = event.player.getEntityData();

		System.out.println(playerData.getString("Channel"));
		System.out.println(playerData.hasKey("Channel"));
		
		ChannelObject channel;
		
		if (playerData.hasKey("Channel"))
			channel = ChannelManager.getChannel(playerData.getString("Channel"));
		else
			return;
		
		if (channel == null)
			return;
		
		if (channel.isLocal())
			localChat(event.player, message);
	}
	
	@SubscribeEvent
	public void constructEntity(EntityEvent.EntityConstructing event) {
		
		if (event.entity instanceof EntityPlayerMP) {
			
			// TODO: Register default channel
			return;
		}
	}
	
	private void localChat(EntityPlayerMP sender, String message) {
		
		for (Object playerEntity : sender.worldObj.playerEntities) {
			
			EntityPlayerMP listener;
			
			if (playerEntity instanceof EntityPlayerMP)
				listener = (EntityPlayerMP) playerEntity;
			else
				continue;
			
			if (sender.equals(listener)) {
				
				ChatMessage.sendMessage(listener, message);
				continue;
			}
			
			if (sender.getPositionVector().distanceTo(listener.getPositionVector()) < 100f)
				ChatMessage.sendMessage(listener, message);
		}
	}
}