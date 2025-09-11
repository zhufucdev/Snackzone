package com.munist.snackzone.mixin;

import com.eightsidedsquare.hideandseek.core.ModRegistries;
import com.eightsidedsquare.hideandseek.datagen.ModDatagen;
import com.munist.snackzone.challenge.CustomChallenges;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.registry.RegistryBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModDatagen.class)
public abstract class ModDatagenMixin {
    @Inject(
            method = "buildRegistry(Lnet/minecraft/registry/RegistryBuilder;)V",
            at = @At("TAIL")
    )
    private void addCustomChallenges(RegistryBuilder registryBuilder, CallbackInfo ci) {
        Log.info(LogCategory.GENERAL, "[SnackZone] Adding custom challenges to Hide and Seek");
        registryBuilder.addRegistry(ModRegistries.CHALLENGE_KEY, CustomChallenges::bootstrap);
    }
}
