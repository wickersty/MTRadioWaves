package me.wickersty.mortuusterra.radiowaves.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;

public class HelpManager {

	@SuppressWarnings("unused")
	private final MTRadioWaves instance;
	
	public HelpManager (MTRadioWaves instance) {
		
		this.instance = instance;
		
	}

	public void showHelp(Player sender) {
		
		sender.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "HELP");
		sender.sendMessage(ChatColor.GRAY + "Using Walkie Talkies:");
		sender.sendMessage(ChatColor.GRAY + "Left Click (with or without shift) to cycle through channels.");
		sender.sendMessage(ChatColor.GRAY + "");
		sender.sendMessage(ChatColor.GRAY + "Using Cellular Phones:");
		sender.sendMessage(ChatColor.GRAY + "Left Click (with or without shift) to cycle through contacts.");
		sender.sendMessage(ChatColor.GRAY + "Right Click to read texts, Right Click with shift to delete a text.");
		sender.sendMessage(ChatColor.GRAY + "");
		sender.sendMessage(ChatColor.AQUA + "/mtr help 2");
		
	}

	public void showHelp2(Player sender) {

		sender.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "HELP (continued)");
		sender.sendMessage(ChatColor.GRAY + "Using Handheld Radios:");
		sender.sendMessage(ChatColor.GRAY + "Left Click (with or without shift) to cycle through channels.");
		sender.sendMessage(ChatColor.GRAY + "Shift+Right Click to scan all channels");
		sender.sendMessage(ChatColor.GRAY + "");
		sender.sendMessage(ChatColor.GRAY + "Commands:");
		sender.sendMessage(ChatColor.AQUA + "/mtr help crafting " + ChatColor.GRAY + "Walkie Talkies, Cellular Phones, and Handheld Radios");
		sender.sendMessage(ChatColor.AQUA + "/mtr help admin " + ChatColor.GRAY + "Admin Commands");
		sender.sendMessage(ChatColor.AQUA + "/mtr help permissions " + ChatColor.GRAY + "Permissions Settings");
		
	}

	public void showHelpCrafting(Player sender) {
		
		sender.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "CRAFTING RECIPES");
		sender.sendMessage(ChatColor.AQUA + "Walkie Talkies");
		sender.sendMessage(ChatColor.GRAY + "Gold Ingot in the middle, redstone everywhere else.");

		sender.sendMessage(ChatColor.GRAY + "");

		sender.sendMessage(ChatColor.AQUA + "Cellular Phones");
		sender.sendMessage(ChatColor.GRAY + "Iron Ingot in the middle, redstone everywhere else.");

		sender.sendMessage(ChatColor.GRAY + "");

		sender.sendMessage(ChatColor.AQUA + "Handheld Radio");
		sender.sendMessage(ChatColor.GRAY + "Just use a compass.");

	}

	public void showHelpAdmin(Player sender) {
		
		sender.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "ADMIN COMMANDS");
		sender.sendMessage(ChatColor.AQUA + "/mtr admin give <player> walkietalkie" + ChatColor.GRAY + "");
		sender.sendMessage(ChatColor.AQUA + "/mtr admin give <player> cellularphone" + ChatColor.GRAY + "");
		sender.sendMessage(ChatColor.AQUA + "/mtr admin give <player> handheldradio" + ChatColor.GRAY + "");

	}

	public void showHelpPermissions(Player sender) {
		
		sender.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "PERMISSIONS");
		sender.sendMessage(ChatColor.AQUA + "mtr.admin" + ChatColor.GRAY + "Admin Commands");
		sender.sendMessage(ChatColor.AQUA + "mtr.walkietalkie " + ChatColor.GRAY + "Player can create and use Walkie Talkies");
		sender.sendMessage(ChatColor.AQUA + "mtr.cellularphone " + ChatColor.GRAY + "Player can create and use Cellular Phones");
		sender.sendMessage(ChatColor.AQUA + "mtr.handheldradio" + ChatColor.GRAY + "Player can create and use Handheld Radios");

	}

}
