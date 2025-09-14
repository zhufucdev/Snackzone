package com.munist.snackzone

import com.mojang.serialization.Codec
import net.minecraft.advancement.criterion.AbstractCriterion
import net.minecraft.predicate.entity.LootContextPredicate
import net.minecraft.server.network.ServerPlayerEntity
import java.util.*

class IgniteCreeperCriteria : AbstractCriterion<IgniteCreeperCriteria.Conditions>() {
    override fun getConditionsCodec(): Codec<Conditions> = Conditions.CODEC

    fun trigger(player: ServerPlayerEntity) {
        trigger(player, Conditions::requirementsMet)
    }

    data class Conditions(val playerPredicate: Optional<LootContextPredicate> = Optional.empty()) :
        AbstractCriterion.Conditions {
        override fun player(): Optional<LootContextPredicate> = playerPredicate

        fun requirementsMet(): Boolean = true

        companion object {
            val CODEC: Codec<Conditions> =
                LootContextPredicate.CODEC.optionalFieldOf("player").xmap(::Conditions, Conditions::player).codec()
        }
    }
}