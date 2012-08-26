package me.wickersty.mortuusterra.radiowaves.listeners;

// import me.wickersty.mortuusterra.events.MTPlayerChatEvent;
import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.devices.CellularPhone;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CellularPhoneListener implements Listener {

	private final MTRadioWaves instance;
	
	public CellularPhoneListener(MTRadioWaves instance) {
		
		this.instance = instance;
		
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void playerSpeaks(AsyncPlayerChatEvent event) {

		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
		
			return;
			
		}
		
		if (!(event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().cellularPhonesItemId)) {
			
			return;
			
		}
		
		event.setCancelled(true);

		playerSpeaksOnCellularPhone(event.getPlayer(), event.getMessage());
		
	}
	
	public void playerSpeaksOnCellularPhone(Player player, String message) {

		if (instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().size() == 0) {
			
			player.sendMessage(ChatColor.DARK_AQUA + "[Cellular Phone] " + ChatColor.GRAY + "You have no contacts. Add some with /mtr add contact playerName.");

			return;
			
		}
		
		if (instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getCurrentCellularPhoneRecipient().equalsIgnoreCase(player.getName())) {
			
			player.sendMessage(ChatColor.DARK_RED + "[MT Radio Waves] " + ChatColor.GRAY + "You're trying to call yourself. Are you schizophrenic?");
			
		}
		
		if (instance.getServer().getPlayer(instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getCurrentCellularPhoneRecipient()) == null) {
			
			// send as text message
			instance.getTextMessageManager().addTextMessage(player.getName(), instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getCurrentCellularPhoneRecipient(), message);
			
			player.sendMessage(ChatColor.DARK_AQUA + "[Cellular Phone] " + ChatColor.GRAY + "Text message sent.");
			
		} else {
			
			// call recipient
			Player recipient = instance.getServer().getPlayer(instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getCurrentCellularPhoneRecipient());
			
			recipient.playEffect(player.getLocation(), Effect.RECORD_PLAY, 1200);
			recipient.sendMessage(ChatColor.DARK_AQUA + "[Cellular Phone] " + ChatColor.DARK_GRAY + "[" + player.getName() + "] " + ChatColor.GRAY + message);

			player.sendMessage(ChatColor.DARK_AQUA + "[Cellular Phone] " + ChatColor.DARK_GRAY + "Called " + instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getCurrentCellularPhoneRecipient() + ": " + ChatColor.GRAY + message);

		}
		
	}

	/*
	@EventHandler(priority=EventPriority.LOW)
	public void playerSpeaksOnCellularPhone(MTPlayerChatEvent event) {

		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (!(event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().cellularPhonesItemId)) {
			
			return;
			
		}

		event.setMessageAction("supress");
		
		Player sender = event.getPlayer();

		playerSpeaksOnCellularPhone(sender, event.getMessage());

	}
	*/

	@EventHandler(priority=EventPriority.NORMAL)
	public void playerUsesCellularPhone(PlayerInteractEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (!(event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().cellularPhonesItemId)) {
			
			return;
			
		}
				
		CellularPhone cellularPhone = new CellularPhone(instance);

		// left clicking a cellular phone toggles recipients
		if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			
			if (event.getPlayer().isSneaking() == true) {
			
				cellularPhone.getPreviousContact(event.getPlayer());
				
			} else {
				
				cellularPhone.getNextContact(event.getPlayer());
				
			}
			
		}
		
		// right clicking a cellular phone checks your message
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			if (event.getPlayer().isSneaking() == true) {
				
				cellularPhone.deleteTextMessage(event.getPlayer());
				
			} else {
				
				cellularPhone.checkTextMessages(event.getPlayer());
				
			}
						
		}		
	}

}
