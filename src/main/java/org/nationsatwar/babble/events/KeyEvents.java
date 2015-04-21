package org.nationsatwar.babble.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.proxy.ClientProxy;

public class KeyEvents {
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		
		if (ClientProxy.chatKey.isPressed()) {
			
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;			
			player.openGui(Babble.instance, 20, player.getEntityWorld(), 0, 0, 0);
		}
	}
}