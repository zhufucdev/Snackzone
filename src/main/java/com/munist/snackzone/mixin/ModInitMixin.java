package com.munist.snackzone.mixin;

import com.eightsidedsquare.hideandseek.common.game.powerup.Powerup;
import com.eightsidedsquare.hideandseek.core.ModInit;
import com.eightsidedsquare.hideandseek.core.ModPowerups;
import com.munist.snackzone.CustomChallenges;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModInit.class)
public class ModInitMixin {
    @Inject(
            method = "onInitialize()V",
            at = @At("TAIL"),
            remap = false
    )
    private void addCustomPowerups(CallbackInfo ci) {
        Log.info(LogCategory.GENERAL, "[SnackZone] Adding custom powerups");
        for (var metadata : CustomChallenges.INSTANCE.getMetadata()) {
            ModPowerups.CHALLENGE_POWERUPS.put(
                    metadata.getKey(),
                    ModInit.REGISTRY.powerup(
                            metadata.getKey().getValue().getPath(),
                            Powerup.challenge(metadata.getKey(), metadata.getWeight())
                    )
            );
        }
    }
}
