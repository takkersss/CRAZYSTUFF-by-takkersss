package fr.takkers.crst.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.Event;
import org.joml.Vector3d;
import org.joml.Vector3i;

import java.util.function.Predicate;

public class ModRayTrace implements BlockGetter {
    private static Predicate<Entity> isVisible =
            entity -> !entity.isSpectator() && entity.isPickable();
    private ServerLevel minecraft;

    @Override
    public BlockEntity getBlockEntity(BlockPos pos) {
        return minecraft.getBlockEntity(pos);
    }

    @Override
    public BlockState getBlockState(BlockPos pos) {
        return minecraft.getBlockState(pos);
    }

    @Override
    public FluidState getFluidState(BlockPos pos) {
        return minecraft.getFluidState(pos);
    }

    public LivingEntity getEntityInCrosshair(double reachDistance, ServerPlayer viewer, ServerLevel level) {
        if (viewer == null) {
            return null;
        }
        minecraft = level;

        Vec3 position = viewer.getEyePosition();
        Vec3 look = viewer.getViewVector(1.0F);
        Vec3 max = position.add(look.x * reachDistance, look.y * reachDistance, look.z * reachDistance);
        AABB searchBox =
                viewer.getBoundingBox().expandTowards(look.scale(reachDistance)).inflate(1.0D, 1.0D, 1.0D);

        EntityHitResult result = ProjectileUtil.getEntityHitResult(viewer, position, max, searchBox,
                isVisible, reachDistance * reachDistance);

        if (result == null || result.getEntity() == null) {
            return null;
        }

        if (result.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) result.getEntity();

            HitResult blockHit =
                    clip(setupRayTraceContext(viewer, reachDistance, ClipContext.Fluid.NONE));

            if (!blockHit.getType().equals(BlockHitResult.Type.MISS)) {
                double blockDistance = blockHit.getLocation().distanceTo(position);
                if (blockDistance > target.distanceTo(viewer)) {
                    return target;
                }
            } else {
                return target;
            }
        }

        return null;
    }

    private ClipContext setupRayTraceContext(Player player, double distance, ClipContext.Fluid fluidHandling) {
        float pitch = player.getXRot();
        float yaw = player.getYRot();
        Vec3 fromPos = player.getEyePosition(1.0F);
        float float_3 = Mth.cos(-yaw * 0.017453292F - 3.1415927F);
        float float_4 = Mth.sin(-yaw * 0.017453292F - 3.1415927F);
        float float_5 = -Mth.cos(-pitch * 0.017453292F);
        float xComponent = float_4 * float_5;
        float yComponent = Mth.sin(-pitch * 0.017453292F);
        float zComponent = float_3 * float_5;
        Vec3 toPos = fromPos.add((double) xComponent * distance, (double) yComponent * distance,
                (double) zComponent * distance);
        return new ClipContext(fromPos, toPos, ClipContext.Block.OUTLINE, fluidHandling, player);
    }

    @Override
    public BlockHitResult clip(ClipContext context) {
        return BlockGetter.traverseBlocks(context.getFrom(), context.getTo(), context, (c, pos) -> {
            BlockState block = this.getBlockState(pos);
            if (!block.canOcclude()) {
                return null;
            }
            VoxelShape voxelshape = c.getBlockShape(block, this, pos);
            return this.clipWithInteractionOverride(c.getFrom(), c.getTo(), pos, voxelshape, block);
        }, (c) -> {
            Vec3 vec3 = c.getFrom().subtract(c.getTo());
            return BlockHitResult.miss(c.getTo(), Direction.getNearest(vec3.x, vec3.y, vec3.z),
                    new BlockPos((int)c.getTo().x, (int)c.getTo().y, (int)c.getTo().z));
        });
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getMinBuildHeight() {
        return 0;
    }
}
