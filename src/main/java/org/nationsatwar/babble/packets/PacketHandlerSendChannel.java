package org.nationsatwar.babble.packets;

import java.util.UUID;

import org.nationsatwar.palette.chat.ChatMessage;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerSendChannel implements IMessageHandler<PacketChannel, IMessage> {

	@Override
	public IMessage onMessage(PacketChannel message, MessageContext ctx) {
		
		EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUUID(UUID.fromString(message.playerUUID));
		player.getEntityData().setString("Channel", message.channel);
		
		ChatMessage.sendMessage(player, "Channel set to: " + message.channel);
		return null;
	}
}