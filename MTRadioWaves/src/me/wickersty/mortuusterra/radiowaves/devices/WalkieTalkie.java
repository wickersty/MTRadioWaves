package me.wickersty.mortuusterra.radiowaves.devices;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.objects.Frequency;
import me.wickersty.mortuusterra.radiowaves.objects.FrequencyRange;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class WalkieTalkie {
	
	private final MTRadioWaves instance;
	private FrequencyRange frequencyRange;
	private Frequency currentFrequency;

	public WalkieTalkie(MTRadioWaves instance, Frequency currentFrequency) {
		
		this.instance = instance;
		
		frequencyRange = instance.getFrequencyManager().getFamilyRadioService();
		
		this.currentFrequency = currentFrequency;
		
	}
	
	public void walkieTalkieRings(Player player, String message, Frequency frequency) {
		
		player.playEffect(player.getLocation(), Effect.RECORD_PLAY, 5);
		player.sendMessage(ChatColor.DARK_AQUA + "[Walkie Talkie] " + ChatColor.DARK_GRAY + "[Ch. " + frequency.getChannel() + "]" + ChatColor.GRAY + message);
		
	}
	
	public void increaseFrequency(Player player) {
		
		currentFrequency = frequencyRange.getNextFrequency(currentFrequency);
		instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).updateCurrentWalkieTalkieFrequency(currentFrequency);
		
		player.sendMessage(ChatColor.DARK_AQUA + "[Walkie Talkie] " + ChatColor.GRAY + "Ch. " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " Mhz)");
		
	}
	
	public void decreaseFrequency(Player player) {
		
		currentFrequency = frequencyRange.getPreviousFrequency(currentFrequency);
		instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).updateCurrentWalkieTalkieFrequency(currentFrequency);
		
		player.sendMessage(ChatColor.DARK_AQUA + "[Walkie Talkie] " + ChatColor.GRAY + "Ch. " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " Mhz)");
		
	}
	
}
