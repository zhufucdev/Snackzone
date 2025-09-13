package com.munist.snackzone.client

import com.eightsidedsquare.hideandseek.core.ModRegistries
import com.munist.snackzone.challenge.CustomChallenges
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class SnackzoneDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()
        pack.addProvider { output, registriesFuture ->
            DynamicRegistryGen(output, registriesFuture)
        }
    }

    override fun buildRegistry(registryBuilder: RegistryBuilder) {
        registryBuilder.addRegistry(ModRegistries.CHALLENGE_KEY, CustomChallenges::bootstrap)
    }

    class DynamicRegistryGen(
        output: FabricDataOutput,
        registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
    ) : FabricDynamicRegistryProvider(output, registriesFuture) {
        override fun configure(
            lookup: RegistryWrapper.WrapperLookup,
            entries: Entries
        ) {
            val challengeRegistry = lookup.getOrThrow(ModRegistries.CHALLENGE_KEY)
            challengeRegistry.streamEntries().forEach { challengeEntry ->
                entries.add(challengeEntry)
            }
        }

        override fun getName(): String {
            return "Snackzone Dynamic Registries"
        }
    }
}

