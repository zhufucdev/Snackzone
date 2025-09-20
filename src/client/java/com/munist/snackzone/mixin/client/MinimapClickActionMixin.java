package com.munist.snackzone.mixin.client;

import com.eightsidedsquare.hideandseek.client.screen.MinimapWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(value = MinimapWidget.ClickAction.class, remap = false)
public class MinimapClickActionMixin {
    @Invoker("<init>")
    private static MinimapWidget.ClickAction init(String javaName, int id, String name, boolean hasTexture) {
        throw new AssertionError();
    }

    @Shadow
    @Final
    @Mutable
    private static MinimapWidget.ClickAction[] $VALUES;

    static {
        final var values = new ArrayList<>(Arrays.asList($VALUES));
        values.add(init("TOGGLE_SHADER", values.size(), "toggle_shader", true));
        $VALUES = values.toArray(new MinimapWidget.ClickAction[0]);
    }
}
