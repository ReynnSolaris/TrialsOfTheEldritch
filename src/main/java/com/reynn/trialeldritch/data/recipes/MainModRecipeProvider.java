package com.reynn.trialeldritch.data.recipes;

import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.data.recipes.provider.FurnaceRecipeProvider;
import com.reynn.trialeldritch.data.recipes.provider.NormalCraftingTableRecipeProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class MainModRecipeProvider extends RecipeProvider {

	protected final DataGenerator generator;
	private final CompletableFuture<Provider> registries;
	
	public MainModRecipeProvider(DataGenerator generator, CompletableFuture<Provider> pRegistries) {
		super(generator.getPackOutput(), pRegistries);
		
		this.generator = generator;
		this.registries = pRegistries;
	}

	@Override
	protected void buildRecipes(RecipeOutput pRecipeOutput) {
		// TODO Auto-generated method stub
		new NormalCraftingTableRecipeProvider(generator, registries, pRecipeOutput).build();;
		new FurnaceRecipeProvider(generator, registries, pRecipeOutput).build();;
	}
	
	public ResourceLocation getModId(String path) {
		return ResourceLocation.fromNamespaceAndPath(TrialEldritch.MODID, path);
	}
}
