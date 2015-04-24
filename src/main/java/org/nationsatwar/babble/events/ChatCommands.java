package org.nationsatwar.babble.events;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

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
			
			sender.addChatMessage(new ChatComponentText("Invalid arguments"));
			return;
		}
		
		System.out.println("Test");
		// TODO: Functionality
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