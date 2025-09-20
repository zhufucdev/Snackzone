package com.munist.snackzone.client

import com.eightsidedsquare.hideandseek.client.screen.MinimapWidget

object MinimapClickActionExtension {
    @JvmField
    val toggleShader: MinimapWidget.ClickAction = MinimapWidget.ClickAction.valueOf("TOGGLE_SHADER")
}
