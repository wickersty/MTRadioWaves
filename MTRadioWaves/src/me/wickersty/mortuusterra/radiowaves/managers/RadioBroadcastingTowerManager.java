package me.wickersty.mortuusterra.radiowaves.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.devices.RadioBroadcastingTower;
import me.wickersty.mortuusterra.radiowaves.objects.Frequency;

public class RadioBroadcastingTowerManager {
	
	private final MTRadioWaves instance;
	private List<RadioBroadcastingTower> radioBroadcastingTowers;
	
	public RadioBroadcastingTowerManager(MTRadioWaves instance) {
		
		this.instance = instance;
		
		radioBroadcastingTowers = new ArrayList<RadioBroadcastingTower>();
		
	}
	
	public void addRadioBroadcastingTower(RadioBroadcastingTower radioTower) {
		
		radioBroadcastingTowers.add(radioTower);

	}

	public void removeRadioBroadcastingTower(RadioBroadcastingTower radioTower) {
		
		radioBroadcastingTowers.remove(radioTower);

	}
	
	public RadioBroadcastingTower getRadioBroadcastingTowerByLocation(Location location) {

		RadioBroadcastingTower foundRadioTower = null;
		
		for (RadioBroadcastingTower radioTower : radioBroadcastingTowers) {
			
			if (radioTower.getLeverLocation().equals(location)) {
				
				foundRadioTower = radioTower;
				
			}
			
		}
		
		return foundRadioTower;
	
	}

	public RadioBroadcastingTower getRadioBroadcastingTowerByButtonLocation(Location location) {

		RadioBroadcastingTower foundRadioTower = null;
		
		for (RadioBroadcastingTower radioTower : radioBroadcastingTowers) {
			
			// instance.getLogger().info("Distance: " + radioTower.getLeverLocation().distance(location));
			
			if (radioTower.getLeverLocation().distance(location) <= 2) {
				
				foundRadioTower = radioTower;
				
			}
			
		}
		
		return foundRadioTower;
	
	}

	public RadioBroadcastingTower getRadioBroadcastingTowerWithinRangeAndFrequency(Frequency frequency, Location location) {

		RadioBroadcastingTower foundRadioTower = null;
		double closestRadioBroadcastingTower = instance.getConfigManager().radioBroadcastingTowersRange;

		for (RadioBroadcastingTower radioTower : radioBroadcastingTowers) {
			
			if (radioTower.getLeverLocation().distance(location) <= instance.getConfigManager().radioBroadcastingTowersRange) {
				
				if (radioTower.getCurrentFrequency().getFrequency().equals(frequency.getFrequency())) {
					
					if (radioTower.getLeverLocation().distance(location) < closestRadioBroadcastingTower) {
						
						foundRadioTower = radioTower;
						closestRadioBroadcastingTower = radioTower.getLeverLocation().distance(location);
						
					}
									
				}
				
			}
			
		}
		
		return foundRadioTower;
		
	}
	
	public HashMap<Frequency,RadioBroadcastingTower> getRadioBroadcastingTowersWithinRange(Location location) {

		HashMap<Frequency,RadioBroadcastingTower> foundRadioTowers;
		foundRadioTowers = new HashMap<Frequency,RadioBroadcastingTower>();
		
		for (RadioBroadcastingTower radioTower : radioBroadcastingTowers) {

			if (radioTower.getLeverLocation().distance(location) <= instance.getConfigManager().radioBroadcastingTowersRange) {
				
				if (foundRadioTowers.containsKey(radioTower.getCurrentFrequency())) {
					
					if (foundRadioTowers.get(radioTower.getCurrentFrequency()).getLeverLocation().distance(location) > radioTower.getLeverLocation().distance(location)) {
					
						foundRadioTowers.put(radioTower.getCurrentFrequency(), radioTower);
						
					}
					
				} else {
					
					foundRadioTowers.put(radioTower.getCurrentFrequency(), radioTower);
					
				}
				
			}
			
		}
		
		return foundRadioTowers;
	
	}
	
	public RadioBroadcastingTower getRadioBroadcastingTowersWithinRangeAndFrequency(Frequency frequency, Location location) {

		RadioBroadcastingTower foundRadioTower = null;
		double closestRadioBroadcastingTower = instance.getConfigManager().radioBroadcastingTowersRange;

		for (RadioBroadcastingTower radioTower : radioBroadcastingTowers) {

			if (radioTower.getLeverLocation().distance(location) <= instance.getConfigManager().radioBroadcastingTowersRange) {

				if (radioTower.getCurrentFrequency().getFrequency().equals(frequency.getFrequency())) {

					if (radioTower.getLeverLocation().distance(location) < closestRadioBroadcastingTower) {

						foundRadioTower = radioTower;
						closestRadioBroadcastingTower = radioTower.getLeverLocation().distance(location);

					}

				}

			}
			
		}

		return foundRadioTower;

	}
	
	public void deliverRadioBroadcasts() {
		
		for (Player player : instance.getServer().getOnlinePlayers()) {
			
			if (instance.getConfigManager().isWorldEnabled(player.getWorld().getName()) == true) {
				
				if (player.getItemInHand().getTypeId() == instance.getConfigManager().handheldRadiosItemId) {
					
					Frequency frequency = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getCurrentHandheldRadioFrequency();
					
					RadioBroadcastingTower radioTower = this.getRadioBroadcastingTowerWithinRangeAndFrequency(frequency, player.getLocation());
					
					if (radioTower != null) {
						
						player.sendMessage(ChatColor.DARK_AQUA + "[Handheld Radio]");
						player.sendMessage(ChatColor.GRAY + "Ch. " + frequency.getChannel() + " (" + frequency.getFrequency() + " Mhz)");
						player.sendMessage(ChatColor.AQUA + radioTower.getBroadcastMessage());

					}
					
				}
				
			}
			
		}
		
	}
	
	

	public void saveRadioBroadcastingTowersToDisk() {
		
		try {

			File dataFile = new File(instance.getDataFolder() + File.separator + "radio-broadcasting-towers.txt");
			dataFile.delete();
			
			dataFile = new File(instance.getDataFolder() + File.separator + "radio-broadcasting-towers.txt");
			dataFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		instance.getLogger().info("Saving RadioBroadcastingTowers To Disk");

		try {
			
			PrintWriter dataFile = new PrintWriter(instance.getDataFolder() + File.separator + "radio-broadcasting-towers.txt");
				
			if (radioBroadcastingTowers.size() > 0) {

				for (RadioBroadcastingTower radioBroadcastingTower : radioBroadcastingTowers) {
					
					String radioBroadcastingTowerString = radioBroadcastingTower.getSerializedObject();
	
					dataFile.println(radioBroadcastingTowerString);
					
				}

			}
			
			dataFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadRadioBroadcastingTowersFromDisk() {
		
		ensureDataFileExists();
		
		instance.getLogger().info("Loading radioBroadcastingTowers From Disk");

		try {

			File dataFile = new File(instance.getDataFolder() + File.separator + "radio-broadcasting-towers.txt");
			Scanner inputFile = new Scanner(dataFile);
			
			while (inputFile.hasNextLine()) {
	
				String radioBroadcastingTowerString = inputFile.nextLine();
				String[] radioBroadcastingTowerArray = radioBroadcastingTowerString.split("~");
				
				Location leverLocation = new Location(instance.getServer().getWorld(radioBroadcastingTowerArray[1]), Double.valueOf(radioBroadcastingTowerArray[2]), Double.valueOf(radioBroadcastingTowerArray[3]), Double.valueOf(radioBroadcastingTowerArray[4]));
				
				Frequency currentFrequency = new Frequency(Integer.valueOf(radioBroadcastingTowerArray[7]), String.valueOf(radioBroadcastingTowerArray[6]));
				
				radioBroadcastingTowers.add(new RadioBroadcastingTower(instance, currentFrequency, radioBroadcastingTowerArray[0], leverLocation, Boolean.valueOf(radioBroadcastingTowerArray[5])));

			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}

	public void ensureDataFileExists() {
		
		instance.getLogger().info("Creating radioBroadcastingTowers File");

		// create file if not exists
		try {
			
			File dataFile = new File(instance.getDataFolder() + File.separator + "radio-broadcasting-towers.txt");
	
			if (dataFile.exists() == false) {
				
				dataFile = new File(instance.getDataFolder() + File.separator + "radio-broadcasting-towers.txt");
				dataFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}
	
	public void sendMessageNotYourRadioTower(Player player) {
		
		player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "You do not own this Radio Broadcast Tower.");
		
	}

	public void sendMessageRadioTowerExists(Player player) {
		
		player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "A Radio Broadcast Tower already exists here.");
		
	}

	public void sendMessageRadioTowerFrequency(Player player, Frequency currentFrequency) {

		player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "Radio Broadcast Tower tuned to Channel " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " Mhz.)");
		
	}

	public void sendMessageRadioTowerPowered(Player player, boolean powered) {
		
		if (powered == true) {
			
			player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "Radio Broadcast Tower turned on.");
			
		} else {
			
			player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "Radio Broadcast Tower turned off.");
			
		}
		
	}


}
