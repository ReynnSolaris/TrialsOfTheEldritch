package com.reynn.trialeldritch.data.recipes.provider;


import com.reynn.trialeldritch.data.recipes.MainModRecipeProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class NormalCraftingTableRecipeProvider extends MainModRecipeProvider {
	private final RecipeOutput recipeOutput;
	
	public NormalCraftingTableRecipeProvider(DataGenerator generator, CompletableFuture<Provider> pRegistries, RecipeOutput recipeOutput) {
		super(generator, pRegistries);
		this.recipeOutput = recipeOutput;
	}

	
	public void build() {

	}

}
