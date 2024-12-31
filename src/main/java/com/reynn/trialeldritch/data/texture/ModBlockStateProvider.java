package com.reynn.trialeldritch.data.texture;

import com.reynn.trialeldritch.TrialEldritch;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {



	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, TrialEldritch.MODID, exFileHelper);
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void registerStatesAndModels() {
		// TODO Auto-generated method stub

	}

	private void normalBlock(Block block) {
		ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
		String pathString = blockKey.getPath();
		simpleBlock(block, models().cubeAll(pathString, modLoc("block/" + pathString)));
		simpleBlockItem(block, models().getExistingFile(modLoc("block/" + pathString)));
	}

	private void normalCutoutBlock(Block block) {
		ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
		String pathString = blockKey.getPath();
		simpleBlock(block, models().cubeAll(pathString, modLoc("block/" + pathString)).renderType("cutout"));
		simpleBlockItem(block, models().getExistingFile(modLoc("block/" + pathString)));
	}

	private void createBlockItem(Block block) {
		ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
		String path = blockKey.getPath();
		simpleBlockItem(block, models().getExistingFile(modLoc("block/" + path)));
	}

	private void createBlockItem(Block block, String name) {
		ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
		simpleBlockItem(
				block,
				models().getExistingFile(ResourceLocation.fromNamespaceAndPath(TrialEldritch.MODID, "block/" + name))
		);
	}

	private void facedBlock(Block block, ResourceLocation sideTexture, ResourceLocation frontTexture, ResourceLocation topTexture) {
		ResourceLocation blockKey = BuiltInRegistries.BLOCK.getKey(block);
		String path = blockKey.getPath();
		horizontalBlock(block, sideTexture, frontTexture, topTexture);
		simpleBlockItem(block, models().getExistingFile(modLoc("block/" + path)));
	}

}
