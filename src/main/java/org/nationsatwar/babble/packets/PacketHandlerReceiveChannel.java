package org.nationsatwar.babble.packets;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerReceiveChannel implements IMessageHandler<PacketChannel, IMessage> {

	@Override
	public IMessage onMessage(PacketChannel message, MessageContext ctx) {
		
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		
		if (player == null) {
			
			System.out.println("Could not find player client.");
			return null;
		}
		
		if (player.getUniqueID().equals(UUID.fromString(message.playerUUID)))
			player.getEntityData().setString("Channel", message.channel);
		
		return null;
	}
}