package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.item.custom.*;
//import net.karen.mccoursemod.item.custom.MccourseFishingRodItem;
import net.karen.mccoursemod.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MccourseMod.MOD_ID);

    // Custom item
    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",
            () -> new Item(new Item.Properties()));

    // Custom advanced item
    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    // Custom food
    public static final RegistryObject<Item> KOHLRABI = ITEMS.register("kohlrabi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.KOHLRABI)) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                            @NotNull List<Component> list, @NotNull TooltipFlag flag) {
                    list.add(Component.translatable("tooltip.mccoursemod.kohlrabi"));
                    super.appendHoverText(stack, context, list, flag);
                }
            });

    // Custom fuel
    public static final RegistryObject<Item> AURORA_ASHES = ITEMS.register("aurora_ashes",
            () -> new FuelItem(new Item.Properties(), 1200));

    // CUSTOM Sword
    public static final RegistryObject<Item> ALEXANDRITE_SWORD = ITEMS.register("alexandrite_sword",
            () -> new SwordItem(ModToolTiers.ALEXANDRITE,
                  new Item.Properties().attributes(
                              SwordItem.createAttributes(ModToolTiers.ALEXANDRITE, 3, -2.4f))));

    // CUSTOM Pickaxe
    public static final RegistryObject<Item> ALEXANDRITE_PICKAXE = ITEMS.register("alexandrite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.ALEXANDRITE,
                  new Item.Properties().attributes(
                            PickaxeItem.createAttributes(ModToolTiers.ALEXANDRITE, 1, -2.8f))));

    // CUSTOM Shovel
    public static final RegistryObject<Item> ALEXANDRITE_SHOVEL = ITEMS.register("alexandrite_shovel",
            () -> new ShovelItem(ModToolTiers.ALEXANDRITE,
                  new Item.Properties().attributes(
                             ShovelItem.createAttributes(ModToolTiers.ALEXANDRITE, 1.5f, -3.0f))));

    // CUSTOM Axe
    public static final RegistryObject<Item> ALEXANDRITE_AXE = ITEMS.register("alexandrite_axe",
            () -> new AxeItem(ModToolTiers.ALEXANDRITE,
                  new Item.Properties().attributes(
                                AxeItem.createAttributes(ModToolTiers.ALEXANDRITE, 6, -3.2f))));

    // CUSTOM Hoe
    public static final RegistryObject<Item> ALEXANDRITE_HOE = ITEMS.register("alexandrite_hoe",
            () -> new HoeItem(ModToolTiers.ALEXANDRITE,
                  new Item.Properties().attributes(
                                HoeItem.createAttributes(ModToolTiers.ALEXANDRITE, 0, -3.0f))));

    // CUSTOM Hammer
    public static final RegistryObject<Item> ALEXANDRITE_HAMMER = ITEMS.register("alexandrite_hammer",
            () -> new HammerItem(ModToolTiers.ALEXANDRITE,
                  new Item.Properties().attributes(
                            PickaxeItem.createAttributes(ModToolTiers.ALEXANDRITE, 7, -3.5f))));

    // CUSTOM Helmet
    public static final RegistryObject<Item> ALEXANDRITE_HELMET = ITEMS.register("alexandrite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                  new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(18))));

    // CUSTOM Chestplate
    public static final RegistryObject<Item> ALEXANDRITE_CHESTPLATE = ITEMS.register("alexandrite_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                  new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(18))));

    // CUSTOM Leggings
    public static final RegistryObject<Item> ALEXANDRITE_LEGGINGS = ITEMS.register("alexandrite_leggings",
            () -> new ModArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                  new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(18))));

    // CUSTOM Boots
    public static final RegistryObject<Item> ALEXANDRITE_BOOTS = ITEMS.register("alexandrite_boots",
            () -> new ModArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                  new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(18))));

    // CUSTOM Horse Armor
    public static final RegistryObject<Item> ALEXANDRITE_HORSE_ARMOR = ITEMS.register("alexandrite_horse_armor",
            () -> new AnimalArmorItem(ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, AnimalArmorItem.BodyType.EQUESTRIAN,
                           false, new Item.Properties().stacksTo(1)));

    // Custom Smithing Template
    public static final RegistryObject<Item> KAUPEN_SMITHING_TEMPLATE =
            ITEMS.register("kaupen_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(
                      ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "kaupen")));

    // Custom Bow
    public static final RegistryObject<Item> KAUPEN_BOW = ITEMS.register("kaupen_bow",
            () -> new BowItem(new Item.Properties().durability(500)));

    // Custom disc
    public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).stacksTo(1)));

    // Custom crop
    public static final RegistryObject<Item> KOHLRABI_SEEDS = ITEMS.register("kohlrabi_seeds",
            () -> new ItemNameBlockItem(ModBlocks.KOHLRABI_CROP.get(), new Item.Properties()));

    // Custom bush crop
    public static final RegistryObject<Item> HONEY_BERRIES = ITEMS.register("honey_berries",
            () -> new ItemNameBlockItem(ModBlocks.HONEY_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.HONEY_BERRY)));

    // Custom spawn egg
    public static final RegistryObject<Item> TRICERATOPS_SPAWN_EGG = ITEMS.register("triceratops_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TRICERATOPS, 0x53524b, 0xdac741, new Item.Properties()));

    // Custom Throwable Projectiles
    public static final RegistryObject<Item> TOMAHAWK = ITEMS.register("tomahawk",
            () -> new TomahawkItem(new Item.Properties().stacksTo(16)));

    // Custom animated texture
    public static final RegistryObject<Item> RADIATION_STAFF = ITEMS.register("radiation_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // Custom fishing rod
//    public static final RegistryObject<Item> MCCOURSE_FISHING_ROD = item("mccourse_fishing_rod",
//            () -> new MccourseFishingRodItem(new Item.Properties().fireResistant()));

    // CUSTOM METHOD - Registry all custom items
    public static RegistryObject<Item> item(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    // CUSTOM METHOD - Registry all items on MccourseMod file
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}