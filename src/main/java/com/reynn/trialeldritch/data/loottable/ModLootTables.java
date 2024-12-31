package com.reynn.trialeldritch.data.loottable;

import com.google.common.collect.Sets;
import com.reynn.trialeldritch.TrialEldritch;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter.Collector;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLootTables extends LootTableProvider {

	public ModLootTables(PackOutput pOutput, CompletableFuture<Provider> pRegistries) {
		super(pOutput, Set.of(), List.of(
				new SubProviderEntry(
						ModBlockLootTables::new,
						LootContextParamSets.BLOCK
				)
				/*
				new SubProviderEntry(
						ModEntityLootTables::new,
						LootContextParamSets.ENTITY
				)*/
		), pRegistries);
		// TODO Auto-generated constructor stub


	}

	
	@Override
	protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext,
			Collector problemreporter$collector) {
		var modLootTablesId = BuiltInLootTables.all()
				.stream()
				.filter(id -> id.registry().getNamespace().equals(TrialEldritch.MODID))
				.collect(Collectors.toSet());
		
		for (var id : Sets.difference(modLootTablesId, writableregistry.keySet())) {
			validationcontext.reportProblem("Missing built-in table: " + id);
		}
		
		writableregistry.forEach((lootTable -> lootTable.validate(validationcontext)));
	}
}
