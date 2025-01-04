package com.reynn.trialeldritch.data.recipes.provider;


import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.data.recipes.MainModRecipeProvider;
import com.reynn.trialeldritch.items.ItemRegistry;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FurnaceRecipeProvider extends MainModRecipeProvider {
	private final RecipeOutput recipeOutput;
	
	public FurnaceRecipeProvider(DataGenerator generator, CompletableFuture<Provider> pRegistries, RecipeOutput recipeOutput) {
		super(generator, pRegistries);
		this.recipeOutput = recipeOutput;
	}

	public void build() {
		//Voidstone
		oreBlasting(this.recipeOutput, List.of(ItemRegistry.VOIDSTONE_ORE.get()), RecipeCategory.MISC, ItemRegistry.VOIDSTONE.get(), 0f, 60, TrialEldritch.MODID);
		oreSmelting(this.recipeOutput, List.of(ItemRegistry.VOIDSTONE_ORE.get()), RecipeCategory.MISC, ItemRegistry.VOIDSTONE.get(), 0f, 120, TrialEldritch.MODID);

	}
}
