package me.wickersty.mortuusterra.radiowaves.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;

public class ConfigManager {

	private final MTRadioWaves instance;
	
	// config files
	private File configFile;
	private FileConfiguration config;
	
	public String[] worldsEnabled;
	public boolean radioBroadcastingTowersEnabled;
	public int radioBroadcastingTowersRange;
	public boolean walkieTalkiesEnabled;
	public int walkieTalkiesItemId;
	public boolean cellularPhonesEnabled;
	public int cellularPhonesItemId;
	public boolean handheldRadiosEnabled;
	public int handheldRadiosItemId;
	
	public ConfigManager (MTRadioWaves instance) {
		
		this.instance = instance;
		
		config = instance.getConfig();
		
		configFile = new File(instance.getDataFolder() + File.separator + "config.yml");

		initializeConfig();
		
		loadConfig();
		
		saveConfig();		

	}

	public void initializeConfig() {
		
		boolean configFileExists = configFile.exists();
		
		if (configFileExists == true) {

			instance.getLogger().info("Config File Exists");

			try {

				config.options().copyDefaults(true);
				config.load(this.configFile);
				
			} catch (Exception e) {

				e.printStackTrace();
				
			}
			
		} else {
			
			instance.getLogger().info("Creating Default Config File");
			
			config.options().copyDefaults(true);
			
		}
				
	}

	public void loadConfig() {
		
		// worlds
		worldsEnabled = config.getString("settings.worlds.enabled").split(",");
		radioBroadcastingTowersEnabled = config.getBoolean("settings.devices.radio-broadcasting-towers.enabled");
		radioBroadcastingTowersRange = config.getInt("settings.devices.radio-broadcasting-towers.range");
		walkieTalkiesEnabled = config.getBoolean("settings.devices.walkie-talkies.enabled");
		walkieTalkiesItemId = config.getInt("settings.devices.walkie-talkies.item-id");
		cellularPhonesEnabled = config.getBoolean("settings.devices.cellular-phones.enabled");
		cellularPhonesItemId = config.getInt("settings.devices.cellular-phones.item-id");
		handheldRadiosEnabled = config.getBoolean("settings.devices.handheld-radios.enabled");
		handheldRadiosItemId = config.getInt("settings.devices.handheld-radios.item-id");
		
	}

	public void saveConfig() {

		try {

			config.save(configFile);
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}

	}

	public boolean isWorldEnabled(String worldName) {
		
		boolean isEnabled = false;
		
		for (String world : worldsEnabled) {
			
			if (world.equalsIgnoreCase(worldName)) {
				
				isEnabled = true;
				
			}
			
		}
		
		return isEnabled;
		
	}

}
