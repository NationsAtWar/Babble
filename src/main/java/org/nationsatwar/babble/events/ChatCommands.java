package org.nationsatwar.babble.events;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import org.nationsatwar.babble.Babble;
import org.nationsatwar.babble.channels.ChannelManager;
import org.nationsatwar.babble.packets.PacketChannel;
import org.nationsatwar.palette.chat.ChatMessage;

public class ChatCommands implements ICommand {
	
	private List<String> aliases;
	
	public ChatCommands() {
		
		aliases = new ArrayList<String>();
		aliases.add("channel");
		aliases.add("chan");
		aliases.add("c");
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	@Override
	public String getName() {
		
		return "channel";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		
		return "channel <channel name>";
	}

	@Override
	public List<String> getAliases() {
		
		return aliases;
	}
	
	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		
		if (args.length == 0) {
			
			sender.addChatMessage(new ChatComponentText("Enter a valid channel name."));
			return;
		}
		
		if (ChannelManager.channelExist(args[0])) {
			
			String channelName = ChannelManager.getChannel(args[0]).getChannelName();
			
			EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
			
			Babble.channel.sendTo(new PacketChannel(player.getUniqueID().toString(), channelName), player);
			
			player.getEntityData().setString("Channel", channelName);
			ChatMessage.sendMessage(player, "Channel set to: " + channelName);
		} else
			sender.addChatMessage(new ChatComponentText("Enter a valid channel name."));
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		
		return true;
	}

	@Override
	public List<?> addTabCompletionOptions(ICommandSender sender, String[] args,
			BlockPos pos) {
		
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		
		return false;
	}
}