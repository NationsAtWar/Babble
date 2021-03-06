package org.nationsatwar.babble;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import org.nationsatwar.babble.configuration.ConfigurationHandler;
import org.nationsatwar.babble.events.ChatCommands;
import org.nationsatwar.babble.events.ChatEvents;
import org.nationsatwar.babble.events.KeyEvents;
import org.nationsatwar.babble.gui.GUIHandler;
import org.nationsatwar.babble.packets.PacketChannel;
import org.nationsatwar.babble.packets.PacketHandlerReceiveChannel;
import org.nationsatwar.babble.packets.PacketHandlerSendChannel;
import org.nationsatwar.babble.proxy.CommonProxy;
 
@Mod(modid = Babble.MODID, 
	name = Babble.MODNAME, 
	version = Babble.MODVER, 
	guiFactory = Babble.GUI_FACTORY_CLASS)
public class Babble {

    @Instance(Babble.MODID)
    public static Babble instance;

	@SidedProxy(clientSide = Babble.CLIENT_PROXY_CLASS, serverSide = Babble.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final String MODID = "babble";
	public static final String MODNAME = "Babble";
	public static final String MODVER = "0.0.1";

	public static final String GUI_FACTORY_CLASS = "org.nationsatwar.babble.gui.GUIFactory";
	public static final String CLIENT_PROXY_CLASS = "org.nationsatwar.babble.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "org.nationsatwar.babble.proxy.CommonProxy";
	
	public static SimpleNetworkWrapper channel;
	
	ChatEvents chatHandler = new ChatEvents();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// Register Event Handlers
		FMLCommonHandler.instance().bus().register(chatHandler);
		MinecraftForge.EVENT_BUS.register(chatHandler);
		
		// Load Configuration
		ConfigurationHandler.loadConfig(event.getSuggestedConfigurationFile());
		
		// Packet Registration
		channel = NetworkRegistry.INSTANCE.newSimpleChannel(Babble.MODID);
		channel.registerMessage(PacketHandlerReceiveChannel.class, PacketChannel.class, 1, Side.CLIENT);
		channel.registerMessage(PacketHandlerSendChannel.class, PacketChannel.class, 1, Side.SERVER);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		// GUI Handler
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
		
		// Handle Client side stuff
		proxy.registerKeybindings();
		proxy.registerGuiEvents();
		FMLCommonHandler.instance().bus().register(new KeyEvents());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		proxy.registerChatOverlay();
	}
	
	@EventHandler
	public void commandEvent(FMLServerStartingEvent event) {
		
		event.registerServerCommand(new ChatCommands());
	}
}