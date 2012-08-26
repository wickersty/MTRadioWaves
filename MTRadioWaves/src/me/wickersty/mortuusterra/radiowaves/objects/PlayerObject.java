package me.wickersty.mortuusterra.radiowaves.objects;

import java.util.ArrayList;
import java.util.List;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;

public class PlayerObject {

	@SuppressWarnings("unused")
	private final MTRadioWaves instance;
	private String playerName;
	private Frequency currentWalkieTalkieFrequency;
	private Frequency currentHandheldRadioFrequency;
	private String currentCellularPhoneRecipient;
	
	private List<String> contacts;

	public PlayerObject(MTRadioWaves instance, String playerName, Frequency currentWalkieTalkieFrequency, String currentCellularPhoneRecipient, Frequency currentHandheldRadioFrequency) {
		
		this.instance = instance;

		this.playerName = playerName;

		this.contacts = new ArrayList<String>();

		if (currentWalkieTalkieFrequency == null) {

			currentWalkieTalkieFrequency = instance.getFrequencyManager().getFamilyRadioService().getFrequencyRange().get(0);
			
		} else {
			
			this.currentWalkieTalkieFrequency = currentWalkieTalkieFrequency;
			
		}
		
		if (currentCellularPhoneRecipient == null) {
			
			currentCellularPhoneRecipient = instance.getPlayerManager().getPlayerObjects().get(0).getPlayerName();
			
		} else {
			
			this.currentCellularPhoneRecipient = currentCellularPhoneRecipient;
			
		}
		
		if (currentHandheldRadioFrequency == null) {

			currentHandheldRadioFrequency = instance.getFrequencyManager().getCitizensBandRadio().getFrequencyRange().get(0);
			
		} else {
			
			this.currentHandheldRadioFrequency = currentHandheldRadioFrequency;
			
		}
		
	}
	
	public String getPlayerName() {
		
		return playerName;
		
	}

	public void updateCurrentWalkieTalkieFrequency(Frequency currentWalkieTalkieFrequency) {
		
		this.currentWalkieTalkieFrequency = currentWalkieTalkieFrequency;
		
	}
	
	public Frequency getCurrentWalkieTalkieFrequency() {
		
		return currentWalkieTalkieFrequency;
		
	}
	
	public void updateCurrentCellularPhoneRecipient(String currentCellularPhoneRecipient) {
		
		this.currentCellularPhoneRecipient = currentCellularPhoneRecipient;
		
	}
	
	public String getCurrentCellularPhoneRecipient() {
		
		return currentCellularPhoneRecipient;
		
	}

	public void updateCurrentHandheldRadioFrequency(Frequency currentHandheldRadioFrequency) {
		
		this.currentHandheldRadioFrequency = currentHandheldRadioFrequency;
		
	}
	
	public Frequency getCurrentHandheldRadioFrequency() {
		
		return currentHandheldRadioFrequency;
		
	}
	
	public List<String> getContacts() {
		
		return contacts;
		
	}
	
	public String getSerializedPlayerObject() {
		
		return playerName + "~" + currentWalkieTalkieFrequency.getFrequency() + "~" + currentWalkieTalkieFrequency.getChannel() + "~" + currentCellularPhoneRecipient + "~" + currentHandheldRadioFrequency.getFrequency() + "~" + currentHandheldRadioFrequency.getChannel();
		
	}
	
}
