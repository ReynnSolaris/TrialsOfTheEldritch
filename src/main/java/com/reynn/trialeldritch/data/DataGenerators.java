package com.reynn.trialeldritch.data;

import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.data.entities.ModEntityProvider;
import com.reynn.trialeldritch.data.lang.ModEnLangProvider;
import com.reynn.trialeldritch.data.loottable.ModLootTables;
import com.reynn.trialeldritch.data.recipes.MainModRecipeProvider;
import com.reynn.trialeldritch.data.sounds.ModSoundProvider;
import com.reynn.trialeldritch.data.tag.ModBlockTagsProvider;
import com.reynn.trialeldritch.data.tag.ModItemTagProvider;
import com.reynn.trialeldritch.data.texture.ModBlockStateProvider;
import com.reynn.trialeldritch.data.texture.ModItemStateProvider;
import com.reynn.trialeldritch.data.worldgen.ModDataMapGenerator;
import com.reynn.trialeldritch.data.worldgen.ModWorldGenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class DataGenerators {
	
	public static void gatherData(GatherDataEvent event) {
		try {
			DataGenerator generator = event.getGenerator();
			PackOutput output = generator.getPackOutput();
			ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
			
			generator.addProvider(true, new ModEnLangProvider(output));
			generator.addProvider(true, new ModItemStateProvider(output, existingFileHelper));
			generator.addProvider(true, new ModBlockStateProvider(output, existingFileHelper));
			generator.addProvider(true, new ModLootTables(output, event.getLookupProvider()));
			
			ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(output, event.getLookupProvider(), existingFileHelper);
			generator.addProvider(true, modBlockTagsProvider);
			generator.addProvider(true, new ModItemTagProvider(output, event.getLookupProvider(), modBlockTagsProvider, existingFileHelper));

			DatapackBuiltinEntriesProvider registryProvider = new ModWorldGenProvider(output, event.getLookupProvider());
			CompletableFuture<HolderLookup.Provider> lookup = registryProvider.getRegistryProvider();
			generator.addProvider(event.includeServer(), registryProvider);

			generator.addProvider(event.includeClient(), new ModEntityProvider(output, lookup, existingFileHelper));

			generator.addProvider(true, new ModSoundProvider(output, event.getExistingFileHelper()));
			
			generator.addProvider(true, new MainModRecipeProvider(generator, event.getLookupProvider()));
			generator.addProvider(true, new ModDataMapGenerator(output, event.getLookupProvider()));

		} catch(RuntimeException e) {
			TrialEldritch.LOGGER.error("Failed to gather data", e);
		}
	}
}
