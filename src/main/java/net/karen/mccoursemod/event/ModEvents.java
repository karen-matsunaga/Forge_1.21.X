package net.karen.mccoursemod.event;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.effect.ModEffects;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.item.custom.HammerItem;
import net.karen.mccoursemod.potion.ModPotions;
import net.karen.mccoursemod.util.ModTags;
import net.karen.mccoursemod.villager.ModVillagers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static net.karen.mccoursemod.util.Util.*;

@Mod.EventBusSubscriber(modid = MccourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if(event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == Items.END_ROD) {
                player.sendSystemMessage(Component.literal(player.getName().getString() + " JUST HIT A SHEEP WITH AN END ROD! YOU SICK FRICK!"));
                sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 5));
                player.getMainHandItem().shrink(1);
            }
        }
    }

    // CUSTOM EVENT - Registry all custom potion recipes
    @SubscribeEvent
    public static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION.getHolder().get());
        builder.addMix(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION.getHolder().get());
        builder.addMix(Potions.AWKWARD, Items.EMERALD, ModPotions.FLY_POTION.getHolder().get());
        builder.addMix(ModPotions.FLY_POTION.getHolder().get(),
                       Blocks.EMERALD_BLOCK.asItem(), ModPotions.FLY_II_POTION.getHolder().get());
        builder.addMix(Potions.AWKWARD, Items.CARROT, ModPotions.HASTE_POTION.getHolder().get());
        builder.addMix(Potions.AWKWARD, Items.GLOWSTONE, ModPotions.NOTHING_POTION.getHolder().get());
        builder.addMix(Potions.AWKWARD, Items.COOKED_BEEF, ModPotions.SATURATION_POTION.getHolder().get());
    }

    // CUSTOM EVENT - Registry all custom villager trades
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.FARMER) {
            var trades = event.getTrades();
            // Custom trade level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.KOHLRABI.get(), 14), 6, 4, 0.05f));
            // Custom trade level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.DIAMOND, 3),
                    new ItemStack(ModItems.HONEY_BERRIES.get(), 32), 6, 4, 0.05f));
            // Custom trade level 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.BELL, 1),
                    new ItemStack(ModItems.AURORA_ASHES.get(), 32), 1, 12, 0.05f));
        }
        if(event.getType() == ModVillagers.KAUPENGER.get()) {
            var trades = event.getTrades();
            // Custom trade level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 12),
                    new ItemStack(ModItems.CHISEL.get(), 1), 6, 4, 0.05f));
            // Custom trade level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.DIAMOND, 19),
                    new ItemStack(ModItems.TOMAHAWK.get(), 1), 6, 4, 0.05f));
            // Custom trade level 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.RADIATION_STAFF.get(), 1), 1, 12, 0.05f));
        }
    }

    // CUSTOM EVENT - Wandering custom trades
    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades(); // Common trades
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades(); // Rare trades
        // Custom Common trades
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 12),
                new ItemStack(ModItems.RADIATION_STAFF.get(), 1), 1, 10, 0.2f
        ));
        // Custom Rare trades
        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemCost(Items.NETHERITE_INGOT, 8),
                new ItemStack(ModItems.BAR_BRAWL_MUSIC_DISC.get(), 1), 1, 10, 0.2f
        ));
    }

    // CUSTOM EVENT - FLY custom effect
    @SubscribeEvent
    public static void flyEffect(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if ((event.phase == TickEvent.Phase.END) && !player.level().isClientSide()) {
            Abilities abilities = player.getAbilities();
            boolean hasItem = (slot(player, EquipmentSlot.HEAD, ModTags.Items.HELMET_FLY) &&
                    slot(player, EquipmentSlot.CHEST, ModTags.Items.CHESTPLATE_FLY) && // Player used FULL ARMOR or FLY EFFECT
                    slot(player, EquipmentSlot.LEGS, ModTags.Items.LEGGINGS_FLY) &&
                    slot(player, EquipmentSlot.FEET, ModTags.Items.BOOTS_FLY)) || player.hasEffect(ModEffects.FLY_EFFECT.getHolder().get());
            if (hasItem) { if (!abilities.mayfly) { abilities.mayfly = true; } } // Player has FULL ARMOR or FLY EFFECT
            // Player hasn't FULL ARMOR or FLY EFFECT
            else { if (abilities.mayfly && !player.isCreative()) { abilities.mayfly = false; abilities.flying = false; } }
            player.onUpdateAbilities();
        }
    }

    // CUSTOM EVENT - Multiplier blocks custom effect with (DATA COMPONENT)
    @SubscribeEvent
    public static void multiplierTest(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level world = player.level(); // Drop custom block
        double x = player.getX(), y = player.getY(), z = player.getZ(); // Player drop position
        BlockState block = event.getState();
        BlockPos pos = event.getPos(); // Block position
        double bX = pos.getX(), bY = pos.getY(), bZ = pos.getZ();
        if (block.is(Blocks.STONE)) {
            event.setCanceled(true); // Cancel DEFAULT event
            world.destroyBlock(pos, false); // Cancel DEFAULT drop
            ItemStack stone = new ItemStack(Blocks.STONE); // Choose one block -> with custom drop
            stone.set(ModDataComponentTypes.MULTIPLIER.get(), 50); // Set int value
            Integer multiplierValue = stone.get(ModDataComponentTypes.MULTIPLIER.get()); // Get int value
            if (multiplierValue != null && multiplierValue > 0) { stone.setCount(multiplierValue); } // Set int value
            ItemEntity item = new ItemEntity(world, bX + 0.5, bY + 0.5, bZ + 0.5, stone); // Drop custom drop
            item.setDeltaMovement(Vec3.ZERO); // Drop position
            world.addFreshEntity(item); // Drop on air
        }
        // Enchanted item
        createEnchantedItem(Items.NETHERITE_SWORD, Map.of(Enchantments.SHARPNESS.getOrThrow(world), 7), world, pos);
        createGroupedEnchantedBook(Map.of(Enchantments.FORTUNE.getOrThrow(world), 10), world, pos);
        ItemStack item = createEnchantedBook(Enchantments.EFFICIENCY.getOrThrow(world), 4); // Enchanted book
        ItemEntity test = new ItemEntity(world, bX + 0.5, bY + 0.5, bZ + 0.5, item); // Drop custom drop
        test.setDeltaMovement(Vec3.ZERO); // Drop position
        world.addFreshEntity(test); // Drop on air
    }
}