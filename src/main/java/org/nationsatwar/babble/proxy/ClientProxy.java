package org.nationsatwar.babble.proxy;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;
import org.nationsatwar.palette.KeyBindings;

public class ClientProxy extends CommonProxy {
	
	public static KeyBinding chatKey;
	
	@Override
	public void registerKeybindings() {
		
		chatKey = KeyBindings.registerKey(Keyboard.KEY_C, "chatKey");
	}
}