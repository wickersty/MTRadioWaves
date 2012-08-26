package me.wickersty.mortuusterra.radiowaves.listeners;

import me.wickersty.mortuusterra.events.MTTimerEvent;
import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.devices.RadioBroadcastingTower;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class RadioBroadcastingTowerListener implements Listener {
	
	private final MTRadioWaves instance;
	
	public RadioBroadcastingTowerListener (MTRadioWaves instance) {
		
		this.instance = instance;
		
	}
	
	/*
	@EventHandler(priority=EventPriority.LOW)
	public void radioBroadcastTowersSendMessages(MTTimeEvent event) {
		
		if (event.getTimeEventType().equalsIgnoreCase("hour")) {
			
			instance.getRadioBroadcastingTowerManager().deliverRadioBroadcasts();
			
		}
		
	}
	*/

	public void radioBroadcastTowersSendMessages(MTTimerEvent event) {
		
		instance.getRadioBroadcastingTowerManager().deliverRadioBroadcasts();
			
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void toggleRadioTowerPower(PlayerInteractEvent event) {
				
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().walkieTalkiesItemId || event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().cellularPhonesItemId || event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().handheldRadiosItemId) {
			
			return;
			
		}
		
		if (event.hasBlock() == false || !(event.getClickedBlock().getType().equals(Material.LEVER))) {
			
			return;
			
		}
		
		RadioBroadcastingTower radioTower = instance.getRadioBroadcastingTowerManager().getRadioBroadcastingTowerByLocation(event.getClickedBlock().getLocation());
		
		if (radioTower == null) {
			
			return;
			
		}
		
		// this IS a Radio Broadcasting Tower
		if (radioTower.getOwner().equalsIgnoreCase(event.getPlayer().getName())) { } else {
			
			instance.getRadioBroadcastingTowerManager().sendMessageNotYourRadioTower(event.getPlayer());
			
			return;
			
		}
		
		// this Radio Broadcast Tower belongs to the player using it
		boolean powered = radioTower.togglePower();
		
		instance.getRadioBroadcastingTowerManager().sendMessageRadioTowerPowered(event.getPlayer(), powered);
		
		if (radioTower.isPowered() == true) {

			instance.getRadioBroadcastingTowerManager().sendMessageRadioTowerFrequency(event.getPlayer(), radioTower.getCurrentFrequency());
			
		}
		
	}

	@EventHandler(priority=EventPriority.LOW)
	public void changeRadioTowerFrequency(PlayerInteractEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if (event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().walkieTalkiesItemId || event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().cellularPhonesItemId || event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().handheldRadiosItemId) {
			
			return;
			
		}
		
		if (event.hasBlock() == false || !(event.getClickedBlock().getType().equals(Material.STONE_BUTTON))) {
			
			return;
			
		}
		
		RadioBroadcastingTower radioTower = instance.getRadioBroadcastingTowerManager().getRadioBroadcastingTowerByButtonLocation(event.getClickedBlock().getLocation());
		
		if (radioTower == null) {
			
			instance.getLogger().info("RadioTower == null");
			return;
			
		}
		
		// this IS a Radio Broadcasting Tower
		if (radioTower.getOwner().equalsIgnoreCase(event.getPlayer().getName())) { } else {
			
			instance.getRadioBroadcastingTowerManager().sendMessageNotYourRadioTower(event.getPlayer());
			
			return;
			
		}
		
		if (event.getClickedBlock().getLocation().equals(radioTower.getDecreaseFrequencyButtonLocation())) {
			
			radioTower.decreaseFrequency(event.getPlayer());
			
		}
		
		if (event.getClickedBlock().getLocation().equals(radioTower.getIncreaseFrequencyButtonLocation())) {
			
			radioTower.increaseFrequency(event.getPlayer());
			
		}
		
	}

}
