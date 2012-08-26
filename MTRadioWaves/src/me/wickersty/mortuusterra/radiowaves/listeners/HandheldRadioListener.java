package me.wickersty.mortuusterra.radiowaves.listeners;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.devices.HandheldRadio;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HandheldRadioListener implements Listener {

	private final MTRadioWaves instance;

	public HandheldRadioListener(MTRadioWaves instance) {
		
		this.instance = instance;
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerChangesHandheldRadioFrequency(PlayerInteractEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().handheldRadiosItemId) { } else {
			
			return;
			
		}
		
		HandheldRadio handheldRadio = new HandheldRadio(instance, instance.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()).getCurrentHandheldRadioFrequency());

		// right clicking a handheld radio scans frequencies for signals
		if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getPlayer().isSneaking() == true) {
			
			// scan frequencies
			handheldRadio.scanFrequencies(event.getPlayer());

		}

		if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			
			if (event.getPlayer().isSneaking() == true) {
				
				handheldRadio.decreaseFrequency(event.getPlayer());
				
			} else {
								
				handheldRadio.increaseFrequency(event.getPlayer());
				
			}
				
		}
		
	}	
	
}
