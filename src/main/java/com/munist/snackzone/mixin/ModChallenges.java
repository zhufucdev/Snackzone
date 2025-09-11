package com.munist.snackzone.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.registry.Registerable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = {"com.eightsidedsquare.hideandseek.core.ModChallenges"})
public interface ModChallenges {
    @Inject(at = @At("HEAD"), method = "bootstrap(Lnet/minecraft/registry/Registerable;)V")
    private static void bootstrap(Registerable<?> registerable, CallbackInfo ci) {
        Log.debug(LogCategory.GENERAL, "ModChallenges bootstrap got hooked");
    }
}
