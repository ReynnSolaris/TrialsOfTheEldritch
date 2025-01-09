package com.reynn.trialeldritch.data.lang;


import com.reynn.trialeldritch.TrialEldritch;
import com.reynn.trialeldritch.blocks.BlockRegistry;
import com.reynn.trialeldritch.items.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnLangProvider extends LanguageProvider {

	public ModEnLangProvider(PackOutput output) {
		super(output, TrialEldritch.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		//Items
		addItem(ItemRegistry.VOIDSTONE_ORE, "Voidstone Chunk");
		addItem(ItemRegistry.VOIDSTONE, "Voidstone");
		addItem(ItemRegistry.STARSHARD, "Starshard");
		addItem(ItemRegistry.RAW_STARSHARD, "Raw Starshard");
		addItem(ItemRegistry.AETHERIUM_CRYSTAL, "Aetherium Crystal");
		addItem(ItemRegistry.CELESTIAL_ALLOY, "Celestial Alloy");

		addBlock(BlockRegistry.ALTAR, "Alchemist Altar");
	}
}
