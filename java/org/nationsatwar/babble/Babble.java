package org.nationsatwar.babble;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import org.nationsatwar.babble.events.KeyEvents;
import org.nationsatwar.babble.events.ChatEvents;
import org.nationsatwar.babble.gui.GUIHandler;
import org.nationsatwar.babble.packets.PacketSendChannel;
import org.nationsatwar.babble.packets.PacketHandlerSendChannel;
import org.nationsatwar.babble.proxy.CommonProxy;
import org.nationsatwar.babble.utility.ChatMessage;
 
@Mod(modid = Babble.MODID, name = Babble.MODNAME, version = Babble.MODVER)
public class Babble {

    @Instance(Babble.MODID)
    public static Babble instance;

	@SidedProxy(clientSide = Babble.CLIENT_PROXY_CLASS, serverSide = Babble.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final String MODID = "babble";
	public static final String MODNAME = "Babble";
	public static final String MODVER = "0.0.1";
	
	public static final String CLIENT_PROXY_CLASS = "org.nationsatwar.babble.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "org.nationsatwar.babble.proxy.CommonProxy";
	
	public static SimpleNetworkWrapper playgroundChannel;

	// <Key: plotOwner | Value: <Key: plotID | Value: int[0] = plotX, int[1] = plotZ>>
	public static Map<String, Map<Integer, int[]>> plotKeys = new HashMap<String, Map<Integer, int[]>>();
	
	ChatEvents chatHandler = new ChatEvents();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		FMLCommonHandler.instance().bus().register(chatHandler);
		MinecraftForge.EVENT_BUS.register(chatHandler);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.registerRenders();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
		playgroundChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Babble.MODID);
		playgroundChannel.registerMessage(PacketHandlerSendChannel.class, PacketSendChannel.class, 1, Side.SERVER);
		
		proxy.registerKeybindings();
		FMLCommonHandler.instance().bus().register(new KeyEvents());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}