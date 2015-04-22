package org.nationsatwar.babble.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketSendChannel implements IMessage {
	
	public String playerUUID;
	public String channel;
	
	public PacketSendChannel() {
		
	}
	
	public PacketSendChannel(String playerUUID, String channel) {
		
		this.playerUUID = playerUUID;
		this.channel = channel;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		playerUUID = ByteBufUtils.readUTF8String(buf);
		channel = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
		ByteBufUtils.writeUTF8String(buf, playerUUID);
		ByteBufUtils.writeUTF8String(buf, channel);
	}
}