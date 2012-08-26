package me.wickersty.mortuusterra.radiowaves.managers;

import me.wickersty.mortuusterra.radiowaves.MTRadioWaves;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingManager {

	private final MTRadioWaves instance;
	
	public CraftingManager(MTRadioWaves instance) {
		
		this.instance = instance;
		
		initializeRecipes();
		
	}
	
	public void initializeRecipes() {
		
		instance.getLogger().info("Initializing Recipes");
		
		ShapedRecipe walkieTalkieRecipe = new ShapedRecipe(new ItemStack(instance.getConfigManager().walkieTalkiesItemId));
		walkieTalkieRecipe.shape(new String[] { "BBB", "BAB", "BBB" });
		walkieTalkieRecipe.setIngredient('A', Material.GOLD_INGOT);
		walkieTalkieRecipe.setIngredient('B', Material.REDSTONE);
		instance.getServer().addRecipe(walkieTalkieRecipe);
		
		ShapedRecipe cellularPhoneRecipe = new ShapedRecipe(new ItemStack(instance.getConfigManager().cellularPhonesItemId));
		cellularPhoneRecipe.shape(new String[] { "BBB", "BAB", "BBB" });
		cellularPhoneRecipe.setIngredient('A', Material.IRON_INGOT);
		cellularPhoneRecipe.setIngredient('B', Material.REDSTONE);
		instance.getServer().addRecipe(cellularPhoneRecipe);
		
	}

}
