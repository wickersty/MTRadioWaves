package me.wickersty.mortuusterra.radiowaves;

import java.util.logging.Logger;
import me.wickersty.mortuusterra.radiowaves.listeners.CellularPhoneListener;
import me.wickersty.mortuusterra.radiowaves.listeners.HandheldRadioListener;
import me.wickersty.mortuusterra.radiowaves.listeners.PlayerListener;
import me.wickersty.mortuusterra.radiowaves.listeners.RadioBroadcastingTowerListener;
import me.wickersty.mortuusterra.radiowaves.listeners.WalkieTalkieListener;
import me.wickersty.mortuusterra.radiowaves.managers.CommandManager;
import me.wickersty.mortuusterra.radiowaves.managers.ConfigManager;
import me.wickersty.mortuusterra.radiowaves.managers.CraftingManager;
import me.wickersty.mortuusterra.radiowaves.managers.FileManager;
import me.wickersty.mortuusterra.radiowaves.managers.FrequencyManager;
import me.wickersty.mortuusterra.radiowaves.managers.HelpManager;
import me.wickersty.mortuusterra.radiowaves.managers.PermissionsManager;
import me.wickersty.mortuusterra.radiowaves.managers.PlayerManager;
import me.wickersty.mortuusterra.radiowaves.managers.RadioBroadcastingTowerManager;
import me.wickersty.mortuusterra.radiowaves.managers.TextMessageManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class MTRadioWaves extends JavaPlugin {

	private static MTRadioWaves instance;
	
	private static Logger logger = Logger.getLogger("Minecraft");

	private CommandManager commandManager;
	private ConfigManager configManager;
	private HelpManager helpManager;
	private FileManager fileManager;
	private PermissionsManager permissionsManager;
	private CraftingManager craftingManager;

	private PlayerManager playerManager;
	private FrequencyManager frequencyManager;
	private TextMessageManager textMessageManager;
	private RadioBroadcastingTowerManager radioBroadcastingTowerManager;

	private PlayerListener playerListener;
	private RadioBroadcastingTowerListener radioBroadcastingTowerListener;
	private WalkieTalkieListener walkieTalkieListener;
	private CellularPhoneListener cellularPhoneListener;
	private HandheldRadioListener handheldRadioListener;

	public void onEnable() {

		logger.info("|----------|");

		instance = this;

		getDataFolder().mkdir();
		
		commandManager = new CommandManager(instance);
		configManager = new ConfigManager(instance);
		helpManager = new HelpManager(instance);
		fileManager = new FileManager(instance);
		permissionsManager = new PermissionsManager(instance);
		craftingManager = new CraftingManager(instance);

		playerManager = new PlayerManager(instance);
		frequencyManager = new FrequencyManager(instance);
		textMessageManager = new TextMessageManager(instance);
		radioBroadcastingTowerManager = new RadioBroadcastingTowerManager(instance);

		playerListener = new PlayerListener(instance);
		radioBroadcastingTowerListener = new RadioBroadcastingTowerListener(instance);
		walkieTalkieListener = new WalkieTalkieListener(instance);
		cellularPhoneListener = new CellularPhoneListener(instance);
		handheldRadioListener = new HandheldRadioListener(instance);

		getServer().getPluginManager().registerEvents(this.playerListener, this);
		getServer().getPluginManager().registerEvents(this.radioBroadcastingTowerListener, this);
		getServer().getPluginManager().registerEvents(this.walkieTalkieListener, this);
		getServer().getPluginManager().registerEvents(this.cellularPhoneListener, this);
		getServer().getPluginManager().registerEvents(this.handheldRadioListener, this);
		
		getFileManager().loadFiles();
		
		logger.info("|----------|");

	}
	
	public void onDisable() {
		
		logger.info("|----------|");

		getFileManager().saveFiles();
		
		getServer().getScheduler().cancelTasks(this);

		logger.info("|----------|");
		
	}

	public CommandManager getCommandManager() {
		
		return commandManager;
		
	}
	
	public ConfigManager getConfigManager() {
		
		return configManager;
		
	}
	
	public HelpManager getHelpManager() {
		
		return helpManager;
		
	}

	public FileManager getFileManager() {
		
		return fileManager;
		
	}

	public PermissionsManager getPermissionsManager() {
		
		return permissionsManager;
		
	}	

	public CraftingManager getCraftingManager() {
		
		return craftingManager;
		
	}	
	
	public PlayerManager getPlayerManager() {
		
		return playerManager;
		
	}	

	public FrequencyManager getFrequencyManager() {
		
		return frequencyManager;
		
	}	

	public TextMessageManager getTextMessageManager() {
		
		return textMessageManager;
		
	}	
	
	public RadioBroadcastingTowerManager getRadioBroadcastingTowerManager() {
		
		return radioBroadcastingTowerManager;
		
	}

	public PlayerListener getPlayerListener() {
		
		return playerListener;
		
	}

	public WalkieTalkieListener getWalkieTalkieListener() {
		
		return walkieTalkieListener;
		
	}

	public CellularPhoneListener getCellularPhoneListener() {
		
		return cellularPhoneListener;
		
	}


	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (sender instanceof Player) {
			
			// player commands
			getCommandManager().processCommand((Player) sender, cmd, args);
			
			
		}
		
		return true;
		
	}
	
}
