package org.nationsatwar.babble.gui;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionsRowList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.packets.PacketSendChannel;

public class ChatMenuGUI extends GuiScreen {
	
	private ResourceLocation backgroundimage = new ResourceLocation(Babble.MODID + ":" + 
			"textures/client/gui/GuiBackground.png");
	
	private EntityPlayer player;
	private int windowX, windowY, windowWidth, windowHeight;
	
	public static final int GUI_ID = 20;
	
	private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.ANAGLYPH, GameSettings.Options.VIEW_BOBBING, GameSettings.Options.GUI_SCALE, GameSettings.Options.GAMMA, GameSettings.Options.RENDER_CLOUDS, GameSettings.Options.PARTICLES, GameSettings.Options.USE_FULLSCREEN, GameSettings.Options.ENABLE_VSYNC, GameSettings.Options.MIPMAP_LEVELS, GameSettings.Options.BLOCK_ALTERNATIVES, GameSettings.Options.USE_VBO};

    private GuiListExtended optionsRowList;
    
	public ChatMenuGUI(EntityPlayer player, World world, int x, int y, int z) {
		
		this.player = player;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		
		windowWidth = 256;
		windowHeight = 192;
		windowX = (width - windowWidth) / 2;
		windowY = (height - windowHeight) / 2 - 20;
		
		buttonList.clear();
		
		/*
		for (Channel channel : ChannelManager.channelList) {
			
			
		}
		*/
		
		optionsRowList = new GuiOptionsRowList(this.mc, this.width, this.height * 2, 0, this.height, 55, videoOptions);
		
		GuiButton buyPlot = new GuiButton(0, windowX + 10, windowY + 30, 120, 20, "Test Channel 12");
		buttonList.add(buyPlot);
		
		GuiButton getDeed = new GuiButton(1, windowX + 10, windowY + 55, 120, 20, "Test Channel 2");
		buttonList.add(getDeed);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		
		// Draws the background window
		this.mc.getTextureManager().bindTexture(backgroundimage);
		drawTexturedModalRect(windowX, windowY, 0, 0, windowWidth,  windowHeight);
		
		drawString(fontRendererObj, "Plot Management", windowX + 10, windowY + 10, 0xEE8888);
		
        this.optionsRowList.drawScreen(mouseX, mouseY, renderPartialTicks);
		
		super.drawScreen(mouseX, mouseY, renderPartialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		
		EntityPlayerSP playerSP = (EntityPlayerSP) player;
		
		if (button.id == 0) {
			
			int chunkX = playerSP.chunkCoordX;
			int chunkZ = playerSP.chunkCoordZ;
			
			Babble.playgroundChannel.sendToServer(new PacketSendChannel(playerSP.getName(), chunkX, chunkZ));
		}
		
		player.closeScreen();
	}
}