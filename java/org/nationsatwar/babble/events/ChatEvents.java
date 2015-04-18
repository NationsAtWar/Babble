package org.nationsatwar.babble.events;

import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

import org.nationsatwar.babble.Babble;

import akka.event.Logging.Debug;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatEvents {
	
	@SubscribeEvent
	public void chatMessage(ServerChatEvent event) {
		
		System.out.println(event.username);
	}
}