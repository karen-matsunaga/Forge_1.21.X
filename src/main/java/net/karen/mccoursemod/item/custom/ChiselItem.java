package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.particle.ModParticles;
import net.karen.mccoursemod.sound.ModSounds;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP = Map.of(Blocks.STONE, Blocks.STONE_BRICKS,
    Blocks.END_STONE, Blocks.END_STONE_BRICKS, Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS, Blocks.IRON_BLOCK, Blocks.DIAMOND_BLOCK,
    Blocks.DIRT, ModBlocks.ALEXANDRITE_BLOCK.get());

    public ChiselItem(Properties pProperties) { super(pProperties); }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if (!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) context.getPlayer()),
                        item -> {
                            if (context.getPlayer() != null) {
                                context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                            }
                        });
                level.playSound(null, context.getClickedPos(), ModSounds.CHISEL_USE.get(), SoundSource.BLOCKS);

                // Custom spawn particles
                ((ServerLevel) level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, clickedBlock.defaultBlockState()),
                        context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.0,
                        context.getClickedPos().getZ() + 0.5, 10, 0, 0, 0, 1);

                ((ServerLevel) level).sendParticles(ParticleTypes.HAPPY_VILLAGER,
                        context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.5,
                        context.getClickedPos().getZ() + 0.5, 5, 0, 0, 0, 3);

                // Custom particles
                ((ServerLevel) level).sendParticles(ModParticles.ALEXANDRITE_PARTICLES.get(),
                        context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1.0,
                        context.getClickedPos().getZ() + 0.5, 8, 0, 0, 0, 2);

                // Get COORDINATES data component -> When clicked on block
                context.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(), context.getClickedPos());
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()) { list.add(Component.translatable("tooltip.mccoursemod.chisel.shift_down")); }
        else { list.add(Component.translatable("tooltip.mccoursemod.chisel")); }
        if (stack.get(ModDataComponentTypes.COORDINATES.get()) != null) {
            list.add(Component.literal("Last block changed at " + stack.get(ModDataComponentTypes.COORDINATES.get())));
        }
        super.appendHoverText(stack, context, list, flag);
    }
}