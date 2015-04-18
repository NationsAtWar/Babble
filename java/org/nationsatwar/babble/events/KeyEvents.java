package org.nationsatwar.babble.events;

import org.lwjgl.input.Keyboard;
import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyEvents {
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		
		if (ClientProxy.chatKey.isPressed()) {
			
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;			
			player.openGui(Babble.instance, 20, player.getEntityWorld(), 0, 0, 0);
		}
	}
}