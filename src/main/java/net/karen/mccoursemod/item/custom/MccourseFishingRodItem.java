//package net.karen.mccoursemod.item.custom;
//
//import net.karen.mccoursemod.util.ChatUtil;
//import net.minecraft.network.chat.CommonComponents;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.FishingRodItem;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.TooltipFlag;
//import org.jetbrains.annotations.NotNull;
//import java.util.List;
//
//public class MccourseFishingRodItem extends FishingRodItem {
//    public MccourseFishingRodItem(Item.Properties properties) { super(properties); }
//
//    @Override
//    public boolean isEnchantable(@NotNull ItemStack stack) { return true; } // Mccourse Fishing Rod can be enchanted
//
//    @Override
//    public @NotNull Component getName(@NotNull ItemStack stack) {
//        return Component.translatable(this.getDescriptionId(stack)).withStyle(ChatUtil.purple); // Appears on name item
//    }
//
//    @Override
//    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
//                                @NotNull List<Component> list, @NotNull TooltipFlag flag) {
//        ChatUtil.tooltipLine(list, "More faster than vanilla Fishing Rod", ChatUtil.white); // Appears on tooltip
//        ChatUtil.tooltipLine(list, "Exclusive drops as Salmon, etc.", ChatUtil.darkGray);
//        list.add(CommonComponents.EMPTY);
//        super.appendHoverText(stack, context, list, flag);
//    }
//}