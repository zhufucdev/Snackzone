package com.munist.snackzone.challenge;

import com.eightsidedsquare.hideandseek.common.game.challenge.Challenge;
import com.eightsidedsquare.hideandseek.core.ModInit;
import com.eightsidedsquare.hideandseek.core.ModRegistries;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancement.criterion.ItemDurabilityChangedCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.List;
import java.util.Optional;

public interface CustomChallenges {
    List<RegistryKey<Challenge>> VALUES = new ObjectArrayList<>();
    RegistryKey<Challenge> BREAK_A_WOODEN_HOE = create("break_a_wooden_hoe");

    static void bootstrap(Registerable<Challenge> registerable) {
        RegistryEntryLookup<Item> registryLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);
        register(registerable, BREAK_A_WOODEN_HOE, Challenge.builder()
                .criterion(
                        ItemDurabilityChangedCriterion.Conditions.create(
                                Optional.empty(),
                                Optional.of(
                                        ItemPredicate.Builder.create().items(registryLookup, Items.WOODEN_HOE).build()
                                ),
                                NumberRange.IntRange.atMost(0)
                        )
                )
        );
    }

    private static RegistryKey<Challenge> create(String name) {
        RegistryKey<Challenge> key = ModInit.REGISTRY.key(ModRegistries.CHALLENGE_KEY, name);
        VALUES.add(key);
        return key;
    }

    private static void register(Registerable<Challenge> registerable, RegistryKey<Challenge> key, Challenge.Builder builder) {
        registerable.register(key, builder.translationKey("challenge.snackzone." + key.getValue().getPath()).build());
    }
}
