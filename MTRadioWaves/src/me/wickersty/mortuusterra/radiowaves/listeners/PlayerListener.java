package me.wickersty.mortuusterra.radiowaves.listeners;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	
	private final MTRadioWaves instance;
	
	public PlayerListener(MTRadioWaves instance) {
		
		this.instance = instance;
		
	}

	@EventHandler(priority=EventPriority.LOW)
	public void playerLogsIn(PlayerJoinEvent event) {
		
		if (instance.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()) == null) {
						
			instance.getPlayerManager().addPlayer(event.getPlayer().getName());
			
		}
		
	}

}
