package me.wickersty.mortuusterra.radiowaves.devices;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;
import me.wickersty.mortuusterra.radiowaves.objects.Frequency;
import me.wickersty.mortuusterra.radiowaves.objects.FrequencyRange;

public class RadioBroadcastingTower {
	
	private final MTRadioWaves instance;
	private FrequencyRange frequencyRange;
	private Frequency currentFrequency;
	
	private Location leverLocation;
	private BlockFace towerBlockFace;
	private String owner;
	private boolean powered;
	private String broadcastMessage;

	private Block lever;
	private Block jukeboxLeft;
	private Block jukebox;
	private Block jukeboxRight;
	private Block buttonLeft;
	private Block buttonRight;
	private Block ironFence1;
	private Block ironFence2;
	private Block ironFence3;
	private Block antennaStart;

	public RadioBroadcastingTower(MTRadioWaves instance, Frequency currentFrequency, String owner, Location leverLocation, boolean powered) {
		
		this.instance = instance;
		
		frequencyRange = instance.getFrequencyManager().getCitizensBandRadio();
		
		this.currentFrequency = currentFrequency;
		
		this.leverLocation = leverLocation;
		this.owner = owner;
		this.powered = powered;
				
		loadBroadcastMessageFromSigns();
		
		assignBlocks();
		
		// Lever thisLever = (Lever) lever;
		// thisLever.setPowered(this.powered);
			
		// instance.getLogger().info("Radio Broadcasting Tower created");
		
	}
	
	public void increaseFrequency(Player player) {
		
		currentFrequency = frequencyRange.getNextFrequency(currentFrequency);
		
		player.sendMessage(ChatColor.DARK_AQUA + "[Radio Tower] " + ChatColor.GRAY + "Ch. " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " Mhz)");
		
	}
	
	public void decreaseFrequency(Player player) {
		
		currentFrequency = frequencyRange.getPreviousFrequency(currentFrequency);
		
		player.sendMessage(ChatColor.DARK_AQUA + "[Radio Tower] " + ChatColor.GRAY + "Ch. " + currentFrequency.getChannel() + " (" + currentFrequency.getFrequency() + " Mhz)");
		
	}
	
	public Frequency getCurrentFrequency() {
		
		return currentFrequency;
		
	}

	public String getBroadcastMessage() {
		
		return broadcastMessage;
		
	}
	
	public void setBroadcastMessage(String broadcastMessage) {
		
		this.broadcastMessage = broadcastMessage;
		
	}
	
	public void loadBroadcastMessageFromSigns() {

		lever = leverLocation.getBlock();
		
		boolean isSign = true;
		Block signBlock = new Location(lever.getLocation().getWorld(), lever.getLocation().getX(), lever.getLocation().getY(), lever.getLocation().getZ()).getBlock();

		broadcastMessage = "";

		while (isSign == true) {
			
			signBlock = signBlock.getRelative(BlockFace.UP);
					
			if (signBlock.getType().equals(Material.WALL_SIGN)) {
				
				Sign sign = (Sign) signBlock.getState();

				if (sign.getLine(0).length() > 0) { broadcastMessage += sign.getLine(0) + " "; }
				if (sign.getLine(1).length() > 0) { broadcastMessage += sign.getLine(1) + " "; }
				if (sign.getLine(2).length() > 0) { broadcastMessage += sign.getLine(2) + " "; }
				if (sign.getLine(3).length() > 0) { broadcastMessage += sign.getLine(3) + " "; }
				
			} else {
				
				isSign = false;
				
			}
			
		}
		
	}
	
	public boolean isPowered() {
		
		return powered;
		
	}
	
	public void setIsPowered(boolean powered) {
		
		this.powered = powered;
		
	}
	
	public boolean togglePower() {
		
		if (powered == true) {
			
			powered = false;
			
		} else {
			
			powered = true;
			
			loadBroadcastMessageFromSigns();
			
		}
		
		return powered;
		
	}
	
	public Location getLeverLocation() {
		
		return leverLocation;
		
	}
	
	public void setLeverLocation(Location leverLocation) {
		
		this.leverLocation = leverLocation;
		
	}

	public Location getDecreaseFrequencyButtonLocation() {
		
		return buttonLeft.getLocation();
		
	}
	
	public Location getIncreaseFrequencyButtonLocation() {
		
		return buttonRight.getLocation();
		
	}
	

	public String getOwner() {
		
		return owner;
		
	}
	
	public void setOwner(String owner) {
		
		this.owner = owner;
		
	}
	
	public BlockFace getTowerBlockFace() {
		
		return towerBlockFace;
		
	}
	
	public void assignBlocks() {
		
		instance.getLogger().info(lever.getRelative(BlockFace.NORTH).toString());
		instance.getLogger().info(lever.getRelative(BlockFace.SOUTH).toString());
		instance.getLogger().info(lever.getRelative(BlockFace.EAST).toString());
		instance.getLogger().info(lever.getRelative(BlockFace.WEST).toString());
		
		// determine which way we are facing
		if (lever.getRelative(BlockFace.NORTH).getType().equals(Material.REDSTONE_LAMP_OFF)) {
			
			towerBlockFace = BlockFace.NORTH;
			jukeboxLeft = lever.getRelative(BlockFace.NORTH_WEST);
			jukebox = lever.getRelative(BlockFace.NORTH);
			jukeboxRight = lever.getRelative(BlockFace.NORTH_EAST);
			buttonLeft = jukeboxLeft.getRelative(BlockFace.SOUTH);
			buttonRight = jukeboxRight.getRelative(BlockFace.SOUTH);
			ironFence1 = jukeboxLeft.getRelative(BlockFace.NORTH);
			ironFence2 = jukebox.getRelative(BlockFace.NORTH);
			ironFence3 = jukeboxRight.getRelative(BlockFace.NORTH);
			antennaStart = jukebox.getRelative(BlockFace.UP);

		}

		if (lever.getRelative(BlockFace.SOUTH).getType().equals(Material.REDSTONE_LAMP_OFF)) {
			
			towerBlockFace = BlockFace.SOUTH;
			jukeboxLeft = lever.getRelative(BlockFace.SOUTH_EAST);
			jukebox = lever.getRelative(BlockFace.SOUTH);
			jukeboxRight = lever.getRelative(BlockFace.SOUTH_WEST);
			buttonLeft = jukeboxLeft.getRelative(BlockFace.NORTH);
			buttonRight = jukeboxRight.getRelative(BlockFace.NORTH);
			ironFence1 = jukeboxLeft.getRelative(BlockFace.SOUTH);
			ironFence2 = jukebox.getRelative(BlockFace.SOUTH);
			ironFence3 = jukeboxRight.getRelative(BlockFace.SOUTH);
			antennaStart = jukebox.getRelative(BlockFace.UP);
			
		}

		if (lever.getRelative(BlockFace.EAST).getType().equals(Material.REDSTONE_LAMP_OFF)) {
			
			towerBlockFace = BlockFace.EAST;
			jukeboxLeft = lever.getRelative(BlockFace.NORTH_EAST);
			jukebox = lever.getRelative(BlockFace.EAST);
			jukeboxRight = lever.getRelative(BlockFace.SOUTH_EAST);
			buttonLeft = jukeboxLeft.getRelative(BlockFace.WEST);
			buttonRight = jukeboxRight.getRelative(BlockFace.WEST);
			ironFence1 = jukeboxLeft.getRelative(BlockFace.EAST);
			ironFence2 = jukebox.getRelative(BlockFace.EAST);
			ironFence3 = jukeboxRight.getRelative(BlockFace.EAST);
			antennaStart = jukebox.getRelative(BlockFace.UP);
			
		}

		if (lever.getRelative(BlockFace.WEST).getType().equals(Material.REDSTONE_LAMP_OFF)) {
			
			towerBlockFace = BlockFace.WEST;
			jukeboxLeft = lever.getRelative(BlockFace.NORTH_WEST);
			jukebox = lever.getRelative(BlockFace.WEST);
			jukeboxRight = lever.getRelative(BlockFace.SOUTH_WEST);
			buttonLeft = jukeboxLeft.getRelative(BlockFace.EAST);
			buttonRight = jukeboxRight.getRelative(BlockFace.EAST);
			ironFence1 = jukeboxLeft.getRelative(BlockFace.WEST);
			ironFence2 = jukebox.getRelative(BlockFace.WEST);
			ironFence3 = jukeboxRight.getRelative(BlockFace.WEST);
			antennaStart = jukebox.getRelative(BlockFace.UP);
			
		}
		
		// instance.getLogger().info(towerBlockFace.toString());

	}
	
	public boolean validateRadioBroadcastingTower() {
		
		boolean validStructure = true;
				
		lever = leverLocation.getBlock();
		
		// validate central piece
		if (lever.getType().equals(Material.LEVER)) { } else { validStructure = false; instance.getLogger().info("lever"); }

		jukeboxLeft = null;
		jukebox = null;
		jukeboxRight = null;
		buttonLeft = null;
		buttonRight = null;
		ironFence1 = null;
		ironFence2 = null;
		ironFence3 = null;
		antennaStart = null;

		
		assignBlocks();
		
		if (jukeboxLeft == null) {
			
			return false;
			
		}
		
		if (jukeboxLeft.getType().equals(Material.REDSTONE_LAMP_OFF)) { } else { validStructure = false; instance.getLogger().info("jukeboxLeft"); }
		if (jukebox.getType().equals(Material.REDSTONE_LAMP_OFF)) { } else { validStructure = false; instance.getLogger().info("jukebox"); }
		if (jukeboxRight.getType().equals(Material.REDSTONE_LAMP_OFF)) { } else { validStructure = false; instance.getLogger().info("jukeboxRight"); }
		if (buttonLeft.getType().equals(Material.STONE_BUTTON)) { } else { validStructure = false; instance.getLogger().info("buttonLeft"); }
		if (buttonRight.getType().equals(Material.STONE_BUTTON)) { } else { validStructure = false; instance.getLogger().info("buttonRight"); }
		if (ironFence1.getType().equals(Material.IRON_FENCE)) { } else { validStructure = false; instance.getLogger().info("ironFence1"); }
		if (ironFence2.getType().equals(Material.IRON_FENCE)) { } else { validStructure = false; instance.getLogger().info("ironFence2"); }
		if (ironFence3.getType().equals(Material.IRON_FENCE)) { } else { validStructure = false; instance.getLogger().info("ironFence3"); }
		if (antennaStart.getType().equals(Material.IRON_FENCE)) { } else { validStructure = false; instance.getLogger().info("antennaStart"); }
				
		return validStructure;
		
	}
	
	public String getSerializedObject() {
		
		return owner + "~" + leverLocation.getWorld().getName() + "~" + leverLocation.getX() + "~" + leverLocation.getY() + "~" + leverLocation.getZ() + "~" + powered + "~" + currentFrequency.getFrequency() + "~" + currentFrequency.getChannel();
		
	}

}
