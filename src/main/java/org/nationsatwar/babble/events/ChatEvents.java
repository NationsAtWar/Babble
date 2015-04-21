package org.nationsatwar.babble.events;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvents {
	
	@SubscribeEvent
	public void chatMessage(ServerChatEvent event) {
		
		System.out.println(event.username);
	}
}