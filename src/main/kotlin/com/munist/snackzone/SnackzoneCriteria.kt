package com.munist.snackzone

import net.minecraft.advancement.criterion.Criteria

object SnackzoneCriteria {
    val TOUCH_WORLD_BORDER: TouchWorldBorderCriterion =
        Criteria.register("snackzone:touch_world_border", TouchWorldBorderCriterion())
    val IGNITE_CREEPER: IgniteCreeperCriteria = Criteria.register("snackzone:ignite_creeper", IgniteCreeperCriteria())

    fun init() {

    }
}