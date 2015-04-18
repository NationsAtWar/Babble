package org.nationsatwar.babble.utility;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import org.lwjgl.input.Keyboard;

public class KeyBindings {
	
	/**
	 * Use this function to register a key for your mod.
	 * 
	 * @param keyCode Find keycode by typing 'Keyboard.KEY_(Key)'
	 * @param keyName The name you would like for your key
	 * @return Returns the Key Binding that your mod can now recognize
	 */
	public static KeyBinding registerKey(int keyCode, String keyName) {
		
		KeyBinding newKeyBinding = new KeyBinding("key." + keyName, keyCode, "key.categories.KeyBindings");
		ClientRegistry.registerKeyBinding(newKeyBinding);
		
		return newKeyBinding;
	}
}