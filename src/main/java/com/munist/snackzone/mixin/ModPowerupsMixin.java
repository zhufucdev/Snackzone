package com.munist.snackzone.mixin;

import com.eightsidedsquare.hideandseek.common.game.challenge.Challenge;
import com.eightsidedsquare.hideandseek.common.game.powerup.ChallengePowerup;
import com.eightsidedsquare.hideandseek.common.game.powerup.Powerup;
import com.eightsidedsquare.hideandseek.core.ModChallenges;
import com.eightsidedsquare.hideandseek.core.ModInit;
import com.eightsidedsquare.hideandseek.core.ModPowerups;
import com.munist.snackzone.CustomChallenges;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Map;

@Mixin(value = ModPowerups.class, remap = false)
public interface ModPowerupsMixin {
    /**
     * @author Steve
     * @reason remove unwanted challenges
     */
    @Overwrite
    private static Map<RegistryKey<Challenge>, ChallengePowerup> createChallengePowerups() {
        Map<RegistryKey<Challenge>, ChallengePowerup> map = new Object2ObjectOpenHashMap<>();

        for (RegistryKey<Challenge> challengeKey : ModChallenges.VALUES) {
            if (CustomChallenges.INSTANCE.getUnwantedKeys().contains(challengeKey)) {
                continue;
            }
            map.put(challengeKey, ModInit.REGISTRY.powerup(challengeKey.getValue().getPath(), Powerup.challenge(challengeKey, 1)));
        }

        for (var metadata : CustomChallenges.INSTANCE.getMetadata()) {
            var powerup = ModInit.REGISTRY.powerup(
                    metadata.getKey().getValue().getPath(),
                    Powerup.challenge(metadata.getKey(), metadata.getWeight())
            );
            map.put(metadata.getKey(), powerup);
        }

        return map;
    }
}
