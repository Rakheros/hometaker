package net.rakheros.hometaker.item.custom;

import java.util.Optional;

import java.util.List;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HometakerItem extends Item {

    public HometakerItem(Settings settings) {
        super(settings);
        
    }
    
    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    } 

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity player && user.getWorld() instanceof ServerWorld server) {            
            BlockPos bedPos = player.getSpawnPointPosition();
            ServerWorld worldTarget = server.getServer().getWorld(player.getSpawnPointDimension());
            Optional<Vec3d> posOpt = PlayerEntity.findRespawnPosition(worldTarget, bedPos, DEFAULT_MAX_COUNT, isFireproof(), isDamageable());
            
            Vec3d pos;
            if (posOpt.isPresent()) {
                pos = posOpt.get();
            } else {
                pos = server.getSpawnPos().toCenterPos();
            }

            player.teleport(worldTarget, pos.getX(), pos.getY(), pos.getZ(), player.getYaw(), player.getPitch());
            player.getItemCooldownManager().set(this, 100);
            if (!player.isCreative()) {
                stack.decrement(1);
            }
        } 

        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Consume : Teleports user to their spwan point."));
        return;
    }
}
