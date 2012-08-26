package me.wickersty.mortuusterra.radiowaves.managers;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;

public class PermissionsManager {

	private final MTRadioWaves instance;

	public static Permission permissions;

	public PermissionsManager (MTRadioWaves instance) {
		
		this.instance = instance;

		initializePermissions();

	}

	private void initializePermissions() {
		
		RegisteredServiceProvider<Permission> permissionProvider = instance.getServer().getServicesManager().getRegistration(Permission.class);
		
		if (permissionProvider != null) {

			permissions = (Permission)permissionProvider.getProvider();
			
		}
		
		if (permissions != null) { 
			
			instance.getLogger().info("Permissions Initialized");
			
		} else {
			
			instance.getLogger().warning("Could Not Initialize Permissions");
			
		}
		
	}
	
	public boolean playerHasPermissions(Player player, String permission) {
		
		boolean hasPermissions = false;
		
		if (permissions.has(player, permission)) {
			
			hasPermissions = true;
			
		}
		
		return hasPermissions;		
		
	}

}
