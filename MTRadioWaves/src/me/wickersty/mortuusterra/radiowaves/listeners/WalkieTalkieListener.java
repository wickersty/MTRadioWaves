package me.wickersty.mortuusterra.radiowaves.listeners;

// import me.wickersty.mortuusterra.events.MTPlayerChatEvent;
import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.devices.WalkieTalkie;
import me.wickersty.mortuusterra.radiowaves.objects.Frequency;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class WalkieTalkieListener implements Listener {
	
	private final MTRadioWaves instance;
	
	public WalkieTalkieListener(MTRadioWaves instance) {
		
		this.instance = instance;
		
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void playerSpeaks(AsyncPlayerChatEvent event) {

		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (!(event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().walkieTalkiesItemId)) {
			
			return;
			
		}
		
		event.setCancelled(true);

		// the sender
		Player sender = event.getPlayer();
		Frequency senderFrequency = instance.getPlayerManager().getPlayerObjectByPlayerName(sender.getName()).getCurrentWalkieTalkieFrequency();
		
		// find players in this world with a walkie talkie in their inventory
		for (Player player : event.getPlayer().getWorld().getPlayers()) {
			
			Inventory playerInventory = player.getInventory();
			
			if (playerInventory.contains(instance.getConfigManager().walkieTalkiesItemId)) {
				
				// this player has a walkie talkie, but is it tuned to the same frequency as the sender?
				Player recipient = player;
				Frequency recipientFrequency = instance.getPlayerManager().getPlayerObjectByPlayerName(recipient.getName()).getCurrentWalkieTalkieFrequency();
				
				if (senderFrequency.getChannel() == recipientFrequency.getChannel()) {
					
					// deliver the message over walkie talkie to the recipient
					recipient.playEffect(player.getLocation(), Effect.RECORD_PLAY, 5);
					recipient.sendMessage(ChatColor.DARK_AQUA + "[Walkie Talkie] " + ChatColor.DARK_GRAY + "[" + sender.getName() + " on Ch. " + senderFrequency.getChannel() + "] " + ChatColor.GRAY + event.getMessage());
					
				}
				
			}
			
		}
		
	}
	
	/*
	@EventHandler(priority=EventPriority.HIGH)
	public void playerSpeaksOnWalkieTalkie(MTPlayerChatEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (!(event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().walkieTalkiesItemId)) {
			
			return;
			
		}
		
		// cancel the normal chat event
		event.setMessageAction("supress");
		
		
		// the sender
		Player sender = event.getPlayer();
		Frequency senderFrequency = instance.getPlayerManager().getPlayerObjectByPlayerName(sender.getName()).getCurrentWalkieTalkieFrequency();
		
		// find players in this world with a walkie talkie in their inventory
		for (Player player : event.getPlayer().getWorld().getPlayers()) {
			
			Inventory playerInventory = player.getInventory();
			
			if (playerInventory.contains(instance.getConfigManager().walkieTalkiesItemId)) {
				
				// this player has a walkie talkie, but is it tuned to the same frequency as the sender?
				Player recipient = player;
				Frequency recipientFrequency = instance.getPlayerManager().getPlayerObjectByPlayerName(recipient.getName()).getCurrentWalkieTalkieFrequency();
				
				if (senderFrequency.getChannel() == recipientFrequency.getChannel()) {
					
					// deliver the message over walkie talkie to the recipient
					recipient.playEffect(player.getLocation(), Effect.RECORD_PLAY, 5);
					recipient.sendMessage(ChatColor.DARK_AQUA + "[Walkie Talkie] " + ChatColor.DARK_GRAY + "[" + sender.getName() + " on Ch. " + senderFrequency.getChannel() + "] " + ChatColor.GRAY + event.getMessage());
					
				}
				
			}
			
		}
		
	}
	*/
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerChangesWalkieTalkieFrequency(PlayerInteractEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (!(event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().walkieTalkiesItemId)) {
			
			return;
			
		}
		
		WalkieTalkie walkieTalkie = new WalkieTalkie(instance, instance.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()).getCurrentWalkieTalkieFrequency());

		// right clicking a walkie talkie does nothing
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			
			
		}
		
		// left clicking a walkie talkie decreases its frequency/channel
		if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

			if (event.getPlayer().isSneaking() == true) {

				walkieTalkie.decreaseFrequency(event.getPlayer());
				
			} else {

				walkieTalkie.increaseFrequency(event.getPlayer());
				
			}
			
		}
		
	}

}
