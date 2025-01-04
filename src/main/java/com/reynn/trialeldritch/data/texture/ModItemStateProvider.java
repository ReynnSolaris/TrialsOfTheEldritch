package com.reynn.trialeldritch.data.texture;


import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.items.ItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.LinkedHashMap;

public class ModItemStateProvider extends ItemModelProvider {

	public ModItemStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, TrialEldritch.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
	static {
		trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
		trimMaterials.put(TrimMaterials.IRON, 0.2F);
		trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
		trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
		trimMaterials.put(TrimMaterials.COPPER, 0.5F);
		trimMaterials.put(TrimMaterials.GOLD, 0.6F);
		trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
		trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
		trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
		trimMaterials.put(TrimMaterials.AMETHYST, 1F);
	}

	@Override
	protected void registerModels() {
		basicItem(ItemRegistry.VOIDSTONE_ORE.get());
		basicItem(ItemRegistry.VOIDSTONE.get());
		basicItem(ItemRegistry.STARSHARD.get());
		basicItem(ItemRegistry.RAW_STARSHARD.get());
	}

	private void item(Item item) {
		String name = getItemName(item);
		getBuilder(name)
			.parent(getExistingFile(mcLoc("item/generated")))
			.texture("layer0", "item/" + name);
	}

	private String getItemName(Item item) {
		return BuiltInRegistries.ITEM.getKey(item).toString().replace(TrialEldritch.MODID +":", "");
	}

	private void trimmedArmorItem(DeferredHolder<Item, ArmorItem> itemDeferredItem) {
		final String MOD_ID = TrialEldritch.MODID;

		if (itemDeferredItem.get() instanceof ArmorItem armorItem) {
			trimMaterials.forEach((trimMaterial, value) -> {
				float trimValue = value;

				String armorType = switch(armorItem.getEquipmentSlot()) {
					case HEAD -> "helmet";
					case CHEST -> "chestplate";
					case LEGS -> "leggings";
					case FEET -> "boots";
					default -> "";
				};

				String armorItemPath = armorItem.toString();
				String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
				String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
				ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
				ResourceLocation trimResLoc = ResourceLocation.parse(trimPath);
				ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

				existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

				getBuilder(currentTrimName)
						.parent(new ModelFile.UncheckedModelFile("item/generated"))
						.texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
						.texture("layer1", trimResLoc);

				// Non-trimmed armorItem file (normal variant)
				this.withExistingParent(itemDeferredItem.getId().getPath(),
								mcLoc("item/generated"))
						.override()
						.model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
						.predicate(mcLoc("trim_type"), trimValue).end()
						.texture("layer0",
								ResourceLocation.fromNamespaceAndPath(MOD_ID,
										"item/" + itemDeferredItem.getId().getPath()));
			});
		}
	}
}
