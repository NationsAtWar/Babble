package org.nationsatwar.babble.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;
import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.channels.ChannelObject;
import org.nationsatwar.babble.packets.PacketChannel;

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
	
	@SuppressWarnings("unchecked")
	private void drawButtons() {
		
		windowWidth = 140;
		windowHeight = 180;
		windowX = 20;
		windowY = 20;
		
		buttonList.clear();
		
		if (page > 0)
			buttonList.add(new GuiButton(0, windowX + 10, windowY + windowHeight - 30, 20, 20, "<"));
		if ((ChannelManager.getListSize() - 1) / 4 > page)
			buttonList.add(new GuiButton(1, windowX + windowWidth - 30, windowY + windowHeight - 30, 20, 20, ">"));
		
		int channelID = 2;
		int channelsPerPage = 4;
		
		int startIndex = page * channelsPerPage;
		
		for (int i = 0; i < channelsPerPage; i++) {
			
			if (ChannelManager.getListSize() > startIndex + i) {
				
				ChannelObject channel = ChannelManager.getChannel(startIndex + i);
				
				buttonList.add(new GuiButton(startIndex + channelID + i, windowX + 20, 
						windowY + 50 + (i * 25), 
						100, 20, channel.getChannelName()));
			} else
				break;
		}
	}
	
	@Override
	public void initGui() {
		
		drawButtons();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		
		// Draws the background window
		this.mc.getTextureManager().bindTexture(backgroundimage);
		drawTexturedModalRect(windowX, windowY, 0, 0, windowWidth,  windowHeight);
		
		float fontScale = 2.0f;
		
		fontRendererObj.setUnicodeFlag(true);

		GL11.glPushMatrix();
		GL11.glScaled(fontScale, fontScale, fontScale);
		drawString(fontRendererObj, "Channel List", (windowX + 25) / (int) fontScale, (windowY + 20) / (int) fontScale, 0xCC0000);
		GL11.glPopMatrix();
		
		fontRendererObj.setUnicodeFlag(false);
		
		super.drawScreen(mouseX, mouseY, renderPartialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		
		/*
		 * I really wish I knew why I needed this part. For whatever reason, when you press the 
		 * 'Next Page' button when on the first page, you will skip the second page. You can go 
		 * to the second page from the third page, but that's the only way. This doesn't happen 
		 * under any other circumstance, no matter what values I give any of these variables.
		 * 
		 * If somebody can figure this out, please tell me. I spent so much of my life worrying 
		 * about this stupid function, I just... I just don't know why... it's not fair. It's not 
		 * fair that something so arbitrary has ruined my life.
		 * 
		 * Fuck you button.isMouseOver() returning false. Fuck you.
		 */
		if(!button.isMouseOver())
			return;
		
		// Previous Page
		if (button.id == 0) {
			
			page -= 1;
			drawButtons();
		}
		// Next Page
		if (button.id == 1) {
			
			page += 1;
			drawButtons();
		}
		
		// Channel buttons
		if (button.id > 1) {
			
			ChannelObject channel = ChannelManager.getChannel(button.id - 2);
			
			player.getEntityData().setString("Channel", channel.getChannelName());
			Babble.channel.sendToServer(new PacketChannel(player.getUniqueID().toString(), channel.getChannelName()));
			player.closeScreen();
		}
	}
}