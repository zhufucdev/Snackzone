package com.munist.snackzone.mixin;

import com.munist.snackzone.SnackzoneCriteria;
import com.munist.snackzone.TouchWorldBorderCriterion;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Shadow
    public abstract ServerWorld getWorld();

    @Inject(method = {"tick()V"}, at = {@At("TAIL")})
    void snackzone$tick(CallbackInfo ci) {
        final var pos = ((ServerPlayerEntity) (Object) this).getPos();
        final var border = getWorld().getWorldBorder();
        final var xBounds = new double[]{border.getBoundEast(), border.getBoundWest()};
        final var zBounds = new double[]{border.getBoundNorth(), border.getBoundSouth()};
        final var pos2d = new Vec2f((float) pos.x, (float) pos.z);
        var minDistance = Float.POSITIVE_INFINITY;
        for (final var x : xBounds) {
            for (final var z : zBounds) {
                final var vertex = new Vec2f((float) x, (float) z);
                final var currentDistance = l0Distance(vertex, pos2d);
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                }
            }
        }
        if (minDistance <= TouchWorldBorderCriterion.TOUCH_WORLD_BORDER_DISTANCE_THRESHOLD) {
            SnackzoneCriteria.INSTANCE.getTOUCH_WORLD_BORDER().trigger((ServerPlayerEntity) (Object) this);
        }
    }

    @Unique
    private static float l0Distance(Vec2f pos1, Vec2f pos2) {
        return Math.min(Math.abs(pos1.x - pos2.x), Math.abs(pos1.y - pos2.y));
    }

}
