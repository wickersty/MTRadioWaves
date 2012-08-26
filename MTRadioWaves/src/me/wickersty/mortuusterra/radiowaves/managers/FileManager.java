package me.wickersty.mortuusterra.radiowaves.managers;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;

public class FileManager {

	private final MTRadioWaves instance;
	
	public FileManager(MTRadioWaves instance) {
		
		this.instance = instance;
		
	}
	
	public void loadFiles() {
		
		instance.getPlayerManager().loadPlayersFromDisk();
		instance.getTextMessageManager().loadtextMessagesFromDisk();
		instance.getRadioBroadcastingTowerManager().loadRadioBroadcastingTowersFromDisk();
		
	}
	
	public void saveFiles() {
		
		instance.getPlayerManager().savePlayersToDisk();
		instance.getTextMessageManager().savetextMessagesToDisk();
		instance.getRadioBroadcastingTowerManager().saveRadioBroadcastingTowersToDisk();
		
	}

}
