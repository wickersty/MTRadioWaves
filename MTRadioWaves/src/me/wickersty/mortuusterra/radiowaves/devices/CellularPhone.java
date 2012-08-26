package me.wickersty.mortuusterra.radiowaves.devices;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.objects.TextMessage;

public class CellularPhone {

	private final MTRadioWaves instance;

	public CellularPhone(MTRadioWaves instance) {
		
		this.instance = instance;
		
	}
	
	public void getPreviousContact(Player player) {
				
		String currentRecipient = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getCurrentCellularPhoneRecipient();
		String previousRecipient = "";

		// instance.getLogger().info("Current Contact: " + currentRecipient);

		int contactsIndex = 0;
		
		if (instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().size() == 0) {
			
			player.sendMessage(ChatColor.DARK_AQUA + "[Cellular Phone] " + ChatColor.GRAY + "You have no contacts. Add some with /mtr add contact playerName.");
			
		} else {
		
			for (String contactName : instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts()) {
				
				if (contactName.equalsIgnoreCase(currentRecipient)) {
					
					if (contactsIndex == 0) {
						
						previousRecipient = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().get(instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().size() - 1);
						
					} else {
						
						previousRecipient = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().get(contactsIndex - 1);
						
					}
					
				}
				
				contactsIndex++;
				
			}
			
			if (currentRecipient.equalsIgnoreCase("")) {
				
				previousRecipient = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().get(0);
				
			}

			instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).updateCurrentCellularPhoneRecipient(previousRecipient);
			player.sendMessage(ChatColor.DARK_AQUA + "[Cellular Phone] " + ChatColor.GRAY + "Current Contact: " + previousRecipient);

		}
			
	}
	
	public void getNextContact(Player player) {
		
		String currentRecipient = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getCurrentCellularPhoneRecipient();
		String nextRecipient = "";

		int contactsIndex = 0;
		
		if (instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().size() == 0) {
			
			player.sendMessage(ChatColor.DARK_AQUA + "[Cellular Phone] " + ChatColor.GRAY + "You have no contacts. Add some with /mtr add contact playerName.");
			
		} else {
		
			for (String contactName : instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts()) {
				
				if (contactName.equalsIgnoreCase(currentRecipient)) {
					
					if (contactsIndex == instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().size() - 1) {
						
						nextRecipient = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().get(0);
						
					} else {
						
						nextRecipient = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().get(contactsIndex + 1);
						
					}
					
				}
				
				contactsIndex++;
				
			}

			if (currentRecipient.equalsIgnoreCase("")) {
				
				nextRecipient = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).getContacts().get(0);
				
			}

			instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).updateCurrentCellularPhoneRecipient(nextRecipient);
			player.sendMessage(ChatColor.DARK_AQUA + "[Cellular Phone] " + ChatColor.GRAY + "Current Contact: " + nextRecipient);

		}
			
	}
	
	public void checkTextMessages(Player player) {
		
		if (instance.getTextMessageManager().getTextMessagesByRecipientName(player.getName()).size() == 0) {
			
			player.sendMessage(ChatColor.DARK_AQUA + "[Text Messages] " + ChatColor.DARK_GRAY + "No text messages.");
			
			return;
			
		}

		// get the most recent text message from the queue
		TextMessage unreadTextMessage = instance.getTextMessageManager().getTextMessagesByRecipientName(player.getName()).get(instance.getTextMessageManager().getTextMessagesByRecipientName(player.getName()).size() - 1);
		
		String messageStatus = "Old";
		if (unreadTextMessage.isUnread() == true) {
			
			messageStatus = "New";
			
		}
		
		player.sendMessage(ChatColor.DARK_AQUA + "[Text Messages] " + ChatColor.DARK_GRAY + "[" + messageStatus + "] [" + unreadTextMessage.getSenderName() + "] " + ChatColor.GRAY + unreadTextMessage.getTextMessage());
		instance.getTextMessageManager().markTextMessageRead(unreadTextMessage);

	}
	
	public void deleteTextMessage(Player player) {
		
		TextMessage textMessage = instance.getTextMessageManager().getTextMessagesByRecipientName(player.getName()).get(0);
		
		instance.getTextMessageManager().removeTextMessage(textMessage.getSenderName(), textMessage.getRecipientName(), textMessage.getTextMessage());
		instance.getServer().getPlayer(textMessage.getRecipientName()).sendMessage(ChatColor.DARK_AQUA + "[Text Message Deleted]");
		
	}
	
	
	
}
