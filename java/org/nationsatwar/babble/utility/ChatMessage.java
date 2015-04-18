package org.nationsatwar.babble.utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class ChatMessage {
	
	public static void sendMessage(EntityPlayer player, String message) {
		
		player.getCommandSenderEntity().addChatMessage(new ChatComponentText(message));
	}
}