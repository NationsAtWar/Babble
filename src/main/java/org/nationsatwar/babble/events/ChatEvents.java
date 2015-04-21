package org.nationsatwar.babble.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvents {
	
	@SubscribeEvent
	public void chatMessage(ServerChatEvent event) {
		
		NBTTagCompound playerData = event.player.getEntityData();
		
		if (playerData.hasKey("Channel"))
			return;
	}
	
	@SubscribeEvent
	public void constructEntity(EntityEvent.EntityConstructing event) {
		
		if (event.entity instanceof EntityPlayerMP) {
			
			// TODO: Register default channel
			return;
		}
	}
}