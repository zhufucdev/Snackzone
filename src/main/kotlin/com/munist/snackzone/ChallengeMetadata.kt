package com.munist.snackzone

import com.eightsidedsquare.hideandseek.common.game.challenge.Challenge
import net.minecraft.registry.RegistryKey

data class ChallengeMetadata(val key: RegistryKey<Challenge>, val weight: Int)
