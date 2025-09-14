package com.munist.snackzone

import com.eightsidedsquare.hideandseek.common.game.challenge.Challenge
import com.eightsidedsquare.hideandseek.core.ModInit
import com.eightsidedsquare.hideandseek.core.ModRegistries
import net.minecraft.advancement.criterion.ItemDurabilityChangedCriterion
import net.minecraft.item.Items
import net.minecraft.predicate.NumberRange
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import java.util.*

object CustomChallenges {
    private val _metadata = mutableSetOf<ChallengeMetadata>()
    val metadata: Set<ChallengeMetadata> get() = _metadata

    val BREAK_A_WOODEN_HOE: RegistryKey<Challenge> = register("break_a_wooden_hoe")

    fun builders(lut: RegistryWrapper.WrapperLookup): Map<RegistryKey<Challenge>, Challenge.Builder> {
        val itemLookup = lut.getOptional(RegistryKeys.ITEM).get()
        return mapOf(
            BREAK_A_WOODEN_HOE to Challenge.builder()
                .criterion(
                    ItemDurabilityChangedCriterion.Conditions.create(
                        Optional.empty(),
                        Optional.of(
                            ItemPredicate.Builder.create().items(itemLookup, Items.WOODEN_HOE).build()
                        ),
                        NumberRange.IntRange.atMost(0)
                    )
                )
        )
    }

    private fun register(name: String, weight: Int = 1): RegistryKey<Challenge> {
        val key = ModInit.REGISTRY.key(ModRegistries.CHALLENGE_KEY, name)
        _metadata.add(ChallengeMetadata(key, weight))
        return key
    }
}