package com.munist.snackzone

import net.fabricmc.api.ModInitializer

class Snackzone : ModInitializer {

    override fun onInitialize() {
        SnackzoneCriteria.init()
    }
}
