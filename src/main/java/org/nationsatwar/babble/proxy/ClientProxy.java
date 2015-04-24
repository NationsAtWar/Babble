package org.nationsatwar.babble.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

import org.lwjgl.input.Keyboard;
import org.nationsatwar.babble.events.GuiEvents;
import org.nationsatwar.babble.gui.ChatOverlay;
import org.nationsatwar.palette.KeyBindings;

public class ClientProxy extends CommonProxy {
	
	public static KeyBinding chatKey;
	public static KeyBinding cycleLeftKey;
	public static KeyBinding cycleRightKey;

	GuiEvents guiHandler = new GuiEvents();
	
	@Override
	public void registerKeybindings() {
		
		chatKey = KeyBindings.registerKey(Keyboard.KEY_C, "chatKey");
		cycleLeftKey = KeyBindings.registerKey(Keyboard.KEY_LBRACKET, "cycleLeftKey");
		cycleRightKey = KeyBindings.registerKey(Keyboard.KEY_RBRACKET, "cycleRightKey");
	}
	
	@Override
	public void registerGuiEvents() {
		
		FMLCommonHandler.instance().bus().register(guiHandler);
		MinecraftForge.EVENT_BUS.register(guiHandler);
	}
	
	@Override
	public void registerChatOverlay() {
		
		MinecraftForge.EVENT_BUS.register(new ChatOverlay(Minecraft.getMinecraft()));
	}
}