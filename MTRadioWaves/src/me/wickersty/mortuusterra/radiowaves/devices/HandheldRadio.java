package me.wickersty.mortuusterra.radiowaves.devices;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.objects.Frequency;
import me.wickersty.mortuusterra.radiowaves.objects.FrequencyRange;

public class HandheldRadio {

	private final MTRadioWaves instance;
	private FrequencyRange frequencyRange;
	private Frequency currentFrequency;

	public HandheldRadio(MTRadioWaves instance, Frequency currentFrequency) {
		
		this.instance = instance;

		frequencyRange = instance.getFrequencyManager().getCitizensBandRadio();
		
		this.currentFrequency = currentFrequency;

	}
	
	public void increaseFrequency(Player player) {
		
		currentFrequency = frequencyRange.getNextFrequency(currentFrequency);
		instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).updateCurrentHandheldRadioFrequency(currentFrequency);
		
		player.sendMessage(ChatColor.DARK_AQUA + "[Handheld Radio] " + ChatColor.GRAY + "Ch. " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " Mhz)");

		RadioBroadcastingTower radioTower = instance.getRadioBroadcastingTowerManager().getRadioBroadcastingTowerWithinRangeAndFrequency(currentFrequency, player.getLocation());

		final Player finalPlayer = player;
		
		if (radioTower == null) {
			
			@SuppressWarnings("unused")
			int taskId = instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
				public void run() {
					
					finalPlayer.sendMessage(ChatColor.GRAY + "No radio broadcasts found.");

				}
			}, 10);
						
		} else {

			String tmpMessage = radioTower.getBroadcastMessage();
			
			if (tmpMessage.length() == 0) {
				
				tmpMessage = ChatColor.GRAY + "No radio broadcasts found.";
				
			} else {
				
				tmpMessage = ChatColor.AQUA + tmpMessage;
				
			}
			
			final String finalMessage = tmpMessage;

			@SuppressWarnings("unused")
			int taskId = instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
				public void run() {
					
					finalPlayer.sendMessage(finalMessage);

				}
			}, 10);
			
		}
		
	}
	
	public void decreaseFrequency(Player player) {
		
		currentFrequency = frequencyRange.getPreviousFrequency(currentFrequency);
		instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).updateCurrentHandheldRadioFrequency(currentFrequency);
		
		player.sendMessage(ChatColor.DARK_AQUA + "[Handheld Radio] " + ChatColor.GRAY + "Ch. " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " Mhz)");

		RadioBroadcastingTower radioTower = instance.getRadioBroadcastingTowerManager().getRadioBroadcastingTowerWithinRangeAndFrequency(currentFrequency, player.getLocation());

		final Player finalPlayer = player;
		
		if (radioTower == null) {
			
			@SuppressWarnings("unused")
			int taskId = instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
				public void run() {
					
					finalPlayer.sendMessage(ChatColor.GRAY + "No radio broadcasts found.");

				}
			}, 10);
						
		} else {

			String tmpMessage = radioTower.getBroadcastMessage();
			
			if (tmpMessage.length() == 0) {
				
				tmpMessage = ChatColor.GRAY + "No radio broadcasts found.";
				
			} else {
				
				tmpMessage = ChatColor.AQUA + tmpMessage;
				
			}
			
			final String finalMessage = tmpMessage;

			@SuppressWarnings("unused")
			int taskId = instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
				public void run() {
					
					finalPlayer.sendMessage(finalMessage);

				}
			}, 10);
			
		}
		
	}
	
	public void scanFrequencies(Player player) {
		
		HashMap<Frequency,RadioBroadcastingTower> radioTowersWithinRange;
		// radioTowersWithinRange = new HashMap<Frequency,RadioBroadcastingTower>();
		
		radioTowersWithinRange = instance.getRadioBroadcastingTowerManager().getRadioBroadcastingTowersWithinRange(player.getLocation());
		
		if (radioTowersWithinRange.size() == 0) {
			
			player.sendMessage(ChatColor.DARK_AQUA + "[Handheld Radio] " + ChatColor.GRAY + "There are no radio towers broadcasting within range.");
			
		} else {
			
			player.sendMessage(ChatColor.DARK_AQUA + "[Handheld Radio] " + ChatColor.GRAY + "Found " + radioTowersWithinRange.size() + " radio tower signals within range:");
			
			for (Frequency key: radioTowersWithinRange.keySet()) {
				
				player.sendMessage(ChatColor.DARK_GRAY + "Channel: " + radioTowersWithinRange.get(key).getCurrentFrequency().getChannel() + " (" + radioTowersWithinRange.get(key).getCurrentFrequency().getFrequency() + " Mhz)");
				
			}

		}

	}

}
