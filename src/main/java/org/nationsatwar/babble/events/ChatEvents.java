package org.nationsatwar.babble.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.channels.ChannelObject;
import org.nationsatwar.babble.configuration.ConfigurationHandler;
import org.nationsatwar.babble.packets.PacketChannel;
import org.nationsatwar.palette.chat.ChatMessage;
import org.nationsatwar.palette.chat.MessageType;

public class ChatEvents {
	
	@SubscribeEvent
	public void updatePlayerChannel(PlayerLoggedInEvent event) {
		
		String channelName = "";
		
		if (event.player.getEntityData().hasKey("Channel"))
			channelName = event.player.getEntityData().getString("Channel");
		else
			channelName = ChannelManager.getDefaultChannel().getChannelName();
		
		Babble.channel.sendTo(new PacketChannel(event.player.getUniqueID().toString(), channelName), (EntityPlayerMP) event.player);
	}

	@SubscribeEvent
	public void configChangedEvent(OnConfigChangedEvent event) {
		
		ConfigurationHandler.saveConfig();
		
	}
	
	@SubscribeEvent
	public void chatMessage(ServerChatEvent event) {
		
		String message = "";
		
		// Formats the message
		if (isPlayerOp(event.player))
			message = ChatMessage.formatMessage(event.username, event.message, MessageType.ADMIN);
		else
			message = ChatMessage.formatMessage(event.username, event.message, MessageType.NORMAL);
		
		// Disables normal functionality
		event.setCanceled(true);
		
		// Gets the player's active channel
		NBTTagCompound playerData = event.player.getEntityData();
		ChannelObject channel = null;
		
		if (playerData.hasKey("Channel"))
			channel = ChannelManager.getChannel(playerData.getString("Channel"));
		else {
			channel = ChannelManager.getDefaultChannel();
			playerData.setString("Channel", channel.getChannelName());
		}
		
		// Only called if something horrible happened
		if (channel == null) {
			
			System.out.println("Error: Sender's channel returned null");
			event.setCanceled(false);
			return;
		}
		
		// Appends channel name to message
		message = channel.getChannelColor() + "(" + channel.getChannelName() + ") " + message;
		
		// Local Chat functionality
		if (channel.isLocal())
			localChat(event.player, message);
		
		// World Chat functionality
		else if (channel.isWorldOnly())
			worldChat(event.player, message);
		
		// Global Chat functionality
		else if (channel.isGlobal())
			globalChat(event.player, message);
		
		// Op Chat functionality
		else if (channel.isOpOnly())
			opChat(event.player, message);
	}
	
	/**
	 * Local Chat functionality
	 * 
	 * @param sender The speaker
	 * @param message The final message
	 */
	private void localChat(EntityPlayerMP sender, String message) {
		
		for (Object playerEntity : sender.worldObj.playerEntities) {
			
			EntityPlayerMP listener;
			
			if (playerEntity instanceof EntityPlayerMP)
				listener = (EntityPlayerMP) playerEntity;
			else
				continue;
			
			if (sender.getPositionVector().distanceTo(listener.getPositionVector()) < ChannelManager.getLocalRange())
				ChatMessage.sendMessage(listener, message);
		}
	}
	
	/**
	 * World Chat functionality
	 * 
	 * @param sender The speaker
	 * @param message The final message
	 */
	private void worldChat(EntityPlayerMP sender, String message) {
		
		for (Object playerEntity : sender.worldObj.playerEntities) {
			
			EntityPlayerMP listener = (EntityPlayerMP) playerEntity;
			ChatMessage.sendMessage(listener, message);
		}
	}
	
	/**
	 * Global Chat functionality
	 * 
	 * @param sender The speaker
	 * @param message The final message
	 */
	private void globalChat(EntityPlayerMP sender, String message) {
		
		for (WorldServer worldServer : MinecraftServer.getServer().worldServers) {
			
			for (Object playerEntity : worldServer.playerEntities) {
				
				EntityPlayerMP listener = (EntityPlayerMP) playerEntity;
				
				ChatMessage.sendMessage(listener, message);
			}
		}
	}
	
	/**
	 * Op Chat functionality
	 * 
	 * @param sender The speaker
	 * @param message The final message
	 */
	private void opChat(EntityPlayerMP sender, String message) {
		
		ChatMessage.sendMessage(sender, message);
		
		for (Object playerEntity : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
			
			EntityPlayerMP listener = (EntityPlayerMP) playerEntity;
			
			if (listener.equals(sender))
				continue;
			
			if (isPlayerOp(listener))
				ChatMessage.sendMessage(listener, message);
		}
	}
	
	private static boolean isPlayerOp(EntityPlayerMP player) {
		
		for (String opName : MinecraftServer.getServer().getConfigurationManager().getOppedPlayerNames())
			if (player.getName().equals(opName))
				return true;
		
		return false;
	}
}