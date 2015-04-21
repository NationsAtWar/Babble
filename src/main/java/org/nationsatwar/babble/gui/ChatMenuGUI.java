package org.nationsatwar.babble.gui;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;
import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.channels.ChannelObject;

public class ChatMenuGUI extends GuiScreen {
	
	private ResourceLocation backgroundimage = new ResourceLocation(Babble.MODID + ":" + 
			"textures/client/gui/GuiBackground.png");
	
	private EntityPlayer player;
	private int windowX, windowY, windowWidth, windowHeight;
	
	public static final int GUI_ID = 21;
	
	private int page = 0;
    
	public ChatMenuGUI(EntityPlayer player, World world, int x, int y, int z) {
		
		this.player = player;
	}
	
	@Override
	public void initGui() {
		
		windowWidth = 256;
		windowHeight = 192;
		windowX = (width - windowWidth) / 2;
		windowY = (height - windowHeight) / 2;
		
		buttonList.clear();
		
		if (page > 0)
			buttonList.add(new GuiButton(0, windowX + 10, windowY + windowHeight - 30, 40, 20, "<"));
		if ((ChannelManager.channelList.size() - 1) / 4 > page)
			buttonList.add(new GuiButton(1, windowX + windowWidth - 50, windowY + windowHeight - 30, 40, 20, ">"));
		
		int channelID = 2;
		int channelsPerPage = 4;
		
		int startIndex = page * channelsPerPage;
		
		for (int i = 0; i < channelsPerPage; i++) {
			
			if (ChannelManager.channelList.size() > startIndex + i) {
				
				ChannelObject channel = ChannelManager.channelList.get(startIndex + i);
				
				buttonList.add(new GuiButton(startIndex + channelID, windowX + 10, 
						windowY + 50 + (i * 25), 
						120, 20, channel.getChannelName()));
			} else
				break;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		
		// Draws the background window
		this.mc.getTextureManager().bindTexture(backgroundimage);
		drawTexturedModalRect(windowX, windowY, 0, 0, windowWidth,  windowHeight);
		
		GL11.glPushMatrix();
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		drawString(fontRendererObj, "Channel List", windowX - 30, windowY, 0xEE6688);
		GL11.glPopMatrix();
		
		super.drawScreen(mouseX, mouseY, renderPartialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		
		System.out.println("Button: " + button.id);
		
		EntityPlayerSP playerSP = (EntityPlayerSP) player;
		
		if (button.id == 0) {
			page -= 1;
			initGui();
		}
		if (button.id == 1) {
			page += 1;
			initGui();
		}
	}
}