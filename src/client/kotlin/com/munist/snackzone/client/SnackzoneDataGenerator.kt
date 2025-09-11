package com.munist.snackzone.client

import com.eightsidedsquare.hideandseek.datagen.ModDatagen
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

class SnackzoneDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        ModDatagen().onInitializeDataGenerator(fabricDataGenerator)
    }
}
