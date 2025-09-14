package com.munist.snackzone

import net.minecraft.advancement.criterion.Criteria

object SnackzoneCriteria {
    val TOUCH_WORLD_BORDER: TouchWorldBorderCriterion =
        Criteria.register("snackzone:touch_world_border", TouchWorldBorderCriterion())

    fun init() {

    }
}