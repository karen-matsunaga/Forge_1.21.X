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
            ItemEnchantments enchants = targetStack.getEnchantments(); // Get all enchantments and enchantment levels
            if (enchants.isEmpty()) {
                player(player, "The item has no enchantments!", darkRed);
                return InteractionResultHolder.fail(changerStack);
            }
            if (amount < 0) { // Check enchantment levels
                boolean allMin = enchants.entrySet().stream().filter(e -> e.getKey() == e)
                                                             .allMatch(e -> e.getIntValue() <= 1);
                if (allMin) { // All enchantment are with min level is 1
                    player(player, "All enchantments are already at level 1!", aqua);
                    return InteractionResultHolder.fail(changerStack);
                }
            }
            // Create new map with increased levels and store original enchantment and level
            Map<Holder<Enchantment>, Integer> upgraded = new HashMap<>();
            for (Map.Entry<Holder<Enchantment>, Integer> entry : enchants.entrySet()) {
                Holder<Enchantment> enchant = entry.getKey(); // Get enchantment
                int newLevel = entry.getValue() + amount; // Get enchantment level and amount
                upgraded.put(enchant, newLevel);
            }
            // Apply the updated enchantments to the original item
            ItemStack newLevel = targetStack.copy();
            upgraded.forEach((ench, lvl) -> {
                if (lvl > 0) { newLevel.enchant(ench, lvl); }
            });
            ItemEnchantments newEnchantment = newLevel.getEnchantments();
            if (targetStack.getItem() instanceof EnchantedBookItem) { // Enchanted books
                ItemStack newBook = new ItemStack(Items.ENCHANTED_BOOK);
                for (Map.Entry<Holder<Enchantment>, Integer> entry : newEnchantment.entrySet()) {
                    Holder<Enchantment> enchant = entry.getKey(); // Get enchantment
                    int enchLvl = entry.getValue(); // Get enchantment level and amount
                    newBook.enchant(enchant, enchLvl);
                }
                player.setItemInHand(InteractionHand.OFF_HAND, newBook);
            }
            else { EnchantmentHelper.setEnchantments(targetStack, newEnchantment); } // Armor and tools
            itemHurt(player, changerStack);
            changerStack.shrink(1); // Consumes Level Charger
            return InteractionResultHolder.success(changerStack);
        }
        else { return InteractionResultHolder.fail(changerStack); }
    }

    // DEFAULT METHOD - Added TOOLTIP on all Level Charger
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        if (stack.is(ModItems.LEVEL_CHARGER_PLUS.get())) {
            tooltipLine(list, " increase +" + amount + " level.", green);
        }
        else if (stack.is(ModItems.LEVEL_CHARGER_MINUS.get())) {
            tooltipLine(list, " decrease " + amount + " level.", red);
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