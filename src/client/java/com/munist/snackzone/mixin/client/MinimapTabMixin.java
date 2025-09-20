package com.munist.snackzone.mixin.client;

import com.eightsidedsquare.hideandseek.client.screen.MinimapWidget;
import com.eightsidedsquare.hideandseek.client.util.ClientWorldMarkings;
import com.munist.snackzone.client.ClientWorldMarkingsMixinAccessor;
import com.munist.snackzone.client.MinimapClickActionExtension;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextIconButtonWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(targets = {"com.eightsidedsquare.hideandseek.client.screen.SeekerSeekingScreen$MinimapTab"}, remap = false)
public abstract class MinimapTabMixin {
    /**
     * @author Steve
     * @reason special case for toggle shader
     */
    @Overwrite
    private void onPress(ButtonWidget widget, MinimapWidget.ClickAction clickAction) {
        if (clickAction == MinimapClickActionExtension.toggleShader) {
            final var workMarkings = (ClientWorldMarkingsMixinAccessor) ClientWorldMarkings.INSTANCE;
            workMarkings.setEnabled(!workMarkings.getEnabled());
            return;
        }

        this.minimapWidget.setClickAction(clickAction);
        for (TextIconButtonWidget buttonWidget : this.buttonWidgets) {
            buttonWidget.active = !buttonWidget.equals(widget);
        }
    }

    @Shadow
    @Final
    private MinimapWidget minimapWidget;

    @Shadow
    @Final
    private List<TextIconButtonWidget> buttonWidgets;
}
