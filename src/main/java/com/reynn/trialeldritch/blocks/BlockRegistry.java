package com.reynn.trialeldritch.blocks;

import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.blocks.magical.AltarBlock;
import com.reynn.trialeldritch.blocks.magical.AltarBlockEntity;
import com.reynn.trialeldritch.items.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

import java.util.Set;
import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TrialEldritch.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TrialEldritch.MODID);

    public static final DeferredBlock<Block> ALTAR = registerBlock("altar", () -> new AltarBlock(
            BlockBehaviour.Properties.of().strength(3.5f).requiresCorrectToolForDrops().sound(SoundType.WOOD))
    );
    public static final Supplier<BlockEntityType<AltarBlockEntity>> ALTAR_ENTITY = BLOCK_ENTITY_TYPES.register("altar_block_entity",
            () -> BlockEntityType.Builder.of(AltarBlockEntity::new, ALTAR.get()).build(null));

    public static DeferredBlock<Block> registerBlock(String name, Supplier<Block> block) {
        DeferredBlock<Block> blockRegBlock = BLOCKS.register(name, block);
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(blockRegBlock.get(), new Item.Properties()));
        return blockRegBlock;
    }


    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ENTITY_TYPES.register(bus);
    }
}
