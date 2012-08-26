package me.wickersty.mortuusterra.radiowaves.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.objects.Frequency;
import me.wickersty.mortuusterra.radiowaves.objects.PlayerObject;

public class PlayerManager {
	
	private final MTRadioWaves instance;
	private List<PlayerObject> players;
		
	public PlayerManager(MTRadioWaves instance) {
		
		this.instance = instance;
		
		players = new ArrayList<PlayerObject>();
		
	}
	
	public List<PlayerObject> getPlayerObjects() {
		
		return players;
		
	}
	
	public void addPhoneContact(Player player, String contactName) {
		
		if (!getPlayerObjectByPlayerName(player.getName()).getContacts().contains(contactName)) {
			
			getPlayerObjectByPlayerName(player.getName()).getContacts().add(contactName);
			
		} else {
			
			player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "Contact Already Exists");
			
		}
				
	}
	
	public void removePhoneContact(Player player, String contactName) {
		
		if (getPlayerObjectByPlayerName(player.getName()).getContacts().contains(contactName)) {
			
			getPlayerObjectByPlayerName(player.getName()).getContacts().remove(contactName);
			
		} else {
			
			player.sendMessage(ChatColor.DARK_RED + "[MTRadioWaves] " + ChatColor.GRAY + "Contact Does Not Exist");
			
		}
		
	}
	
	public PlayerObject getPlayerObjectByPlayerName(String playerName) {

		PlayerObject foundPlayerObject = null;
		
		for (PlayerObject playerObject : players) {
		
			if (playerObject.getPlayerName().equalsIgnoreCase(playerName)) {
				
				foundPlayerObject = playerObject;
				
			}
				
		}
		
		return foundPlayerObject;
		
	}

	public void addPlayer(String playerName) {
		
		instance.getLogger().info("Adding " + playerName + " to data");
		
		if (players.size() == 0) {
			
			// this is the first user ever!
			players.add(new PlayerObject(instance, playerName, instance.getFrequencyManager().getFamilyRadioService().getFrequencyRange().get(0), playerName, instance.getFrequencyManager().getCitizensBandRadio().getFrequencyRange().get(0)));
			
		} else {
		
			players.add(new PlayerObject(instance, playerName, instance.getFrequencyManager().getFamilyRadioService().getFrequencyRange().get(0), players.get(0).getPlayerName(), instance.getFrequencyManager().getCitizensBandRadio().getFrequencyRange().get(0)));
			
		}
		
	}

	public void removePlayer(String playerName) {
		
		PlayerObject playerObject = getPlayerObjectByPlayerName(playerName);

		players.remove(playerObject);
		
	}
	
	public void savePlayersToDisk() {
		
		try {

			File playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
			playersFile.delete();
			
			playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
			playersFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		instance.getLogger().info("Saving Players To Disk");

		try {
			
			PrintWriter playersFile = new PrintWriter(instance.getDataFolder() + File.separator + "players.txt");
				
			if (players.size() > 0) {

				for (PlayerObject player : players) {
					
					String playerString = player.getSerializedPlayerObject();
	
					playersFile.println(playerString);
					
				}

			}
			
			playersFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadPlayersFromDisk() {
		
		ensurePlayersFileExists();
		
		instance.getLogger().info("Loading Players From Disk");

		try {

			File playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
			Scanner inputFile = new Scanner(playersFile);
			
			while (inputFile.hasNextLine()) {
	
				String playerString = inputFile.nextLine();
				String[] playerArray = playerString.split("~");
				
				Frequency currentWalkieTalkieFrequency = new Frequency(Integer.valueOf(playerArray[2]), String.valueOf(playerArray[1]));
				Frequency currentHandheldRadioFrequency = new Frequency(Integer.valueOf(playerArray[5]), String.valueOf(playerArray[4]));

				players.add(new PlayerObject(instance, playerArray[0], currentWalkieTalkieFrequency, playerArray[3], currentHandheldRadioFrequency));

			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}

	public void ensurePlayersFileExists() {
		
		instance.getLogger().info("Creating Players File");

		// create file if not exists
		try {
			
			File playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
	
			if (playersFile.exists() == false) {
				
				playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
				playersFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}
	
}
