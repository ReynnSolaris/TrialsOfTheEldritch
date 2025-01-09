package com.reynn.trialeldritch.data.loottable;

import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.blocks.BlockRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {

	protected ModBlockLootTables(Provider pRegistries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generate() {
		dropSelf(BlockRegistry.ALTAR.get());
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		// TODO Auto-generated method stub
		return BuiltInRegistries.BLOCK.stream()
				.filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
						.filter(key -> key.getNamespace().equals(TrialEldritch.MODID))
						.isPresent())
				.collect(Collectors.toSet());
	}

}
