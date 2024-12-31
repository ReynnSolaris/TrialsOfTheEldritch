package com.reynn.trialeldritch.data.tag;


import com.reynn.trialeldritch.TrialEldritch;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

	public ModItemTagProvider(PackOutput pOutput, CompletableFuture<Provider> pLookupProvider,
			BlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
		super(pOutput, pLookupProvider, provider.contentsGetter(), TrialEldritch.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addTags(Provider pProvider) {
		// Blocks


	}


}
