package me.wickersty.mortuusterra.radiowaves.managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.devices.RadioBroadcastingTower;

public class CommandManager {

	private final MTRadioWaves instance;
	
	public CommandManager (MTRadioWaves instance) {
		
		this.instance = instance;
		
	}

	public void processCommand(Player player, Command cmd, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("mtr")) {

			if (args.length == 0) {
				
				return;
				
			}

			if (args[0].equalsIgnoreCase("add")) {
				
				if (args[1].equalsIgnoreCase("contact")) {
					
					if (args.length == 3 && !args[2].isEmpty()) {
						
						instance.getPlayerManager().addPhoneContact(player, args[2].toLowerCase());

						player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "Contact Added");
						
						instance.getLogger().info(instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().size() + "");

					}
					
				}
				
			}

			if (args[0].equalsIgnoreCase("remove")) {
				
				if (args[1].equalsIgnoreCase("contact")) {
					
					if (args.length == 3 && !args[2].isEmpty()) {
						
						instance.getPlayerManager().removePhoneContact(player, args[2].toLowerCase());

						player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "Contact Removed");

						instance.getLogger().info(instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().size() + "");

					}
					
				}
				
				
			}

			if (args[0].equalsIgnoreCase("help")) {

				if (args.length == 1) {
					
					instance.getHelpManager().showHelp(player);
					
					return;
					
				}

				if (args[0].equalsIgnoreCase("help")) {

					if (args[1].equalsIgnoreCase("1")) {
						
						instance.getHelpManager().showHelp(player);
						
					}
					
					if (args[1].equalsIgnoreCase("2")) {
						
						instance.getHelpManager().showHelp2(player);
						
					}
										
				}
				
				if (args[1].equalsIgnoreCase("crafting")) {
					
					instance.getHelpManager().showHelpCrafting(player);
					
					return;
										
				}
				
				if (args[1].equalsIgnoreCase("admin")) {
					
					instance.getHelpManager().showHelpAdmin(player);
					
					return;
										
				}
				
				if (args[1].equalsIgnoreCase("permissions")) {
					
					instance.getHelpManager().showHelpPermissions(player);
					
					return;
										
				}
				
				
			}
			
			if ((player.isOp() == true || instance.getPermissionsManager().playerHasPermissions(player, "mtr.admin") || instance.getPermissionsManager().playerHasPermissions(player, "mtr.activate")) && args[0].equalsIgnoreCase("activate")) {
				
				instance.getLogger().info("Activating Radio Broadcasting Tower...");
				
				if (instance.getRadioBroadcastingTowerManager().getRadioBroadcastingTowerByLocation(player.getTargetBlock(null, 100).getLocation()) != null) {

					instance.getRadioBroadcastingTowerManager().sendMessageRadioTowerExists(player);
					
					return;
					
				}
				
				if (args[1].equalsIgnoreCase("radiotower")) {
					
					if (!(player.getTargetBlock(null, 100).getType().equals(Material.LEVER))) {
						
						player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "You must be looking at the lever of the radio tower.");
						
						return;
						
					}
					
					RadioBroadcastingTower radioTower = new RadioBroadcastingTower(instance, instance.getFrequencyManager().getCitizensBandRadio().getFrequencyRange().get(0), player.getName(), player.getTargetBlock(null, 100).getLocation(), false);
					
					if (radioTower.validateRadioBroadcastingTower() == false) {
						
						player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "That is not a valid Radio Tower structure. See " + ChatColor.AQUA + "/mtr help radiotower");
						
						return;
						
					}
					
					instance.getRadioBroadcastingTowerManager().addRadioBroadcastingTower(radioTower);
					
					player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "Radio Broadcasting Tower successfully created. See " + ChatColor.AQUA + "/mtr help radiotower");
					
				}				
				
			}
			
			if ((player.isOp() == true || instance.getPermissionsManager().playerHasPermissions(player, "mtr.admin") == true) && args[0].equalsIgnoreCase("admin")) {

				if (args[1].equalsIgnoreCase("load")) {

					instance.getFileManager().loadFiles();
					player.sendMessage(ChatColor.GRAY + "Data Loaded");
										
				}

				if (args[1].equalsIgnoreCase("save")) {

					instance.getFileManager().saveFiles();
					player.sendMessage(ChatColor.GRAY + "Data Saved");
										
				}
				
				if (args[1].equalsIgnoreCase("give")) {

					Player recipient = instance.getServer().getPlayer(args[2]);

					if (args[3].equalsIgnoreCase("walkietalkie")) {
						
						recipient.getInventory().addItem(new ItemStack(instance.getConfigManager().walkieTalkiesItemId));
						recipient.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "The gods have given you a Walkie Talkie");
						player.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "Walkie Talkie given to " + args[2]);
						
					}

					if (args[3].equalsIgnoreCase("cellularphone")) {
						
						recipient.getInventory().addItem(new ItemStack(instance.getConfigManager().cellularPhonesItemId));
						recipient.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "The gods have given you a Cellular Phone");
						player.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "Cellular Phone given to " + args[2]);

					}

					if (args[3].equalsIgnoreCase("handheldradio")) {
						
						recipient.getInventory().addItem(new ItemStack(instance.getConfigManager().handheldRadiosItemId));
						recipient.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "The gods have given you a Handheld Radio");
						player.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "Handheld Radio given to " + args[2]);
						
					}

				}
				
			}
			
		}
		
	}
	
}
