package com.munist.snackzone.mixin.client;

import com.eightsidedsquare.hideandseek.client.util.ClientWorldMarkings;
import com.eightsidedsquare.hideandseek.common.game.marking.WorldMarkingData;
import com.munist.snackzone.client.ClientWorldMarkingsMixinAccessor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClientWorldMarkings.class, remap = false)
public abstract class ClientWorldMarkingsMixin implements ClientWorldMarkingsMixinAccessor {
    @Shadow
    public abstract WorldMarkingData getData();

    @Unique
    private boolean enabled = true;

    @Override
    @Unique
    public void setEnabled(boolean newValue) {
        enabled = newValue;
        scheduleChunkRenders(getData());
    }

    @Override
    @Unique
    public boolean getEnabled() {
        return enabled;
    }

    @Inject(method = "isMarked(II)Z", at = @At("RETURN"), cancellable = true, remap = false)
    private void snackzone$isMarked(int x, int z, CallbackInfoReturnable<Boolean> cir) {
        if (!enabled) {
            cir.setReturnValue(false);
        }
    }

    @Shadow
    protected abstract void scheduleChunkRenders(@Nullable WorldMarkingData data);
}
