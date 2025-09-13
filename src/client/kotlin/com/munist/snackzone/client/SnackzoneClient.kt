package com.munist.snackzone.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.impl.util.log.Log
import net.fabricmc.loader.impl.util.log.LogCategory

class SnackzoneClient : ClientModInitializer {
    override fun onInitializeClient() {
        Log.configureBuiltin(false, true)
        Log.finishBuiltinConfig()
        Log.info(LogCategory.ENTRYPOINT, "[SnackZone] Hello Communist! We will make it great together.")
    }
}
