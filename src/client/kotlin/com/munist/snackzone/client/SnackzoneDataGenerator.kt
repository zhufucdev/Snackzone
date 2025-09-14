package com.munist.snackzone.client

import com.eightsidedsquare.hideandseek.common.game.challenge.Challenge
import com.eightsidedsquare.hideandseek.core.ModRegistries
import com.mojang.serialization.JsonOps
import com.munist.snackzone.CustomChallenges
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.data.DataOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.DataWriter
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class SnackzoneDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()
        pack.addProvider(::CustomChallengesProvider)
    }

    inner class CustomChallengesProvider(
        output: FabricDataOutput,
        private val registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
    ) : DataProvider {
        val pathResolver: DataOutput.PathResolver = output.getResolver(ModRegistries.CHALLENGE_KEY)

        override fun run(writer: DataWriter): CompletableFuture<*> {
            return this.registriesFuture.thenCompose { lut ->
                val opts = lut.getOps(JsonOps.INSTANCE)
                val futures = CustomChallenges.builders(lut)
                    .mapValues { (key, builder) ->
                        builder.translationKey("challenge.hideandseek." + key.value.path).build()
                    }
                    .map { (key, challenge) ->
                        val json = Challenge.CODEC.encodeStart(opts, challenge)
                            .getOrThrow(::IllegalStateException).asJsonObject
                        DataProvider.writeToPath(writer, json, pathResolver.resolveJson(key))
                    }
                CompletableFuture.allOf(*futures.toTypedArray())
            }
        }

        override fun getName(): String {
            return "Snackzone Dynamic Registries"
        }
    }
}

