package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
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
                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        if (Screen.hasShiftDown()) { list.add(Component.translatable("tooltip.mccoursemod.chisel.shift_down")); }
        else { list.add(Component.translatable("tooltip.mccoursemod.chisel")); }
        super.appendHoverText(stack, context, list, flag);
    }
}