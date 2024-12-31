package com.reynn.trialeldritch.data.sounds;


import com.reynn.trialeldritch.TrialEldritch;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundProvider extends SoundDefinitionsProvider {
    public ModSoundProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, TrialEldritch.MODID, helper);
    }

    @Override
    public void registerSounds() {

    }



}
