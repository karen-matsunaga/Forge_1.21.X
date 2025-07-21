package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static net.karen.mccoursemod.util.ChatUtil.*;
import static net.karen.mccoursemod.util.Util.*;

public class LevelChargerItem extends Item {
    private final int amount;

    public LevelChargerItem(Properties properties, int amount) { super(properties);
        this.amount = amount;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player,
                                                           @NotNull InteractionHand hand) {
        InteractionHand otherHand = (hand == mainHand) ? offhand : mainHand; // Player's MAIN HAND and OFFHAND
        ItemStack changerStack = player.getItemInHand(hand),
                   targetStack = player.getItemInHand(otherHand);
        if (!player.level().isClientSide() && changerStack.is(ModTags.Items.LEVEL_CHARGER_GENERAL)) {
            // Get all enchantments and enchantment levels
            ItemEnchantments allEnch = EnchantmentHelper.getEnchantmentsForCrafting(targetStack);
            if (allEnch.isEmpty()) {
                player(player, "The item has no enchantments!", darkRed);
                return InteractionResultHolder.fail(changerStack);
            }
            if (amount < 0) { // Check enchantment levels
                boolean allMin = allEnch.entrySet().stream().allMatch(e -> e.getIntValue() <= 1);
                if (allMin) { // All enchantment are with min level is 1
                    player(player, "All enchantments are already at level 1!", aqua);
                    return InteractionResultHolder.fail(changerStack);
                }
            }
            // Create new map with increased levels and store original enchantment and level
            Map<Holder<Enchantment>, Integer> upgraded = new HashMap<>();
            for (Map.Entry<Holder<Enchantment>, Integer> entry : allEnch.entrySet()) {
                Holder<Enchantment> enchant = entry.getKey(); // Get enchantment
                int newLevel = Math.max(1, entry.getValue() + amount); // Get enchantment level
                upgraded.put(enchant, newLevel); // Store new enchantment and new enchantment level
            }
            // Apply the updated enchantments to the original item
            ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(allEnch);
            for (Map.Entry<Holder<Enchantment>, Integer> entry : upgraded.entrySet()) {
                Holder<Enchantment> key = entry.getKey(); // Set enchantment
                Integer lvl = entry.getValue(); // Set enchantment level
                enchantments.set(key, lvl); // Store new enchantment level
            }
            EnchantmentHelper.setEnchantments(targetStack, enchantments.toImmutable()); // New enchantment level
            itemHurt(player, changerStack); // Message on screen
            changerStack.shrink(1); // Consumes Level Charger
            return InteractionResultHolder.success(changerStack);
        }
        else { return InteractionResultHolder.fail(changerStack); }
    }

    // DEFAULT METHOD - Added TOOLTIP on all Level Charger
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        String name = stack.getDescriptionId().replace("item.mccoursemod.", "");
        if (stack.is(ModItems.LEVEL_CHARGER_PLUS.get())) {
            tooltipLine(list, itemLines(splitWord(name)) + " increase +" + amount + " level.", green);
        }
        else if (stack.is(ModItems.LEVEL_CHARGER_MINUS.get())) {
            tooltipLine(list, itemLines(splitWord(name)) + " decrease " + amount + " level.", red);
        }
        super.appendHoverText(stack, context, list, flag);
    }

    // DEFAULT METHOD - Added NAME on all Level Charger -> Translatable en_us.json
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        Component baseName = super.getName(stack);
        if (stack.is(ModItems.LEVEL_CHARGER_PLUS.get())) { return baseName.copy().withStyle(green); }
        else if (stack.is(ModItems.LEVEL_CHARGER_MINUS.get())) { return baseName.copy().withStyle(red); }
        return baseName;
    }

    // CUSTOM METHOD - Message when consumed Level Charger (Plus / Minus) items
    private void itemHurt(Player player, ItemStack chargerStack) {
        String pos = "Increased +", neg = "Decreased ", screen = amount + " level(s)!";
        var itemsTag = ForgeRegistries.ITEMS.tags();
        if (itemsTag != null) {
            if (itemsTag.getTag(ModTags.Items.LEVEL_CHARGER_GENERAL).contains(chargerStack.getItem())) {
                String name = chargerStack.getItem().getDescriptionId();
                boolean isPlus = name.contains("plus"), isMinus = name.contains("minus");
                playerStyleBool(player, isPlus, isMinus, pos + screen, neg + screen, green, red);
            }
        }
    }
}