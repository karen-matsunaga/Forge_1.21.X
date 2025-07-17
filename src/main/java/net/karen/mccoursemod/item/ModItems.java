package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.custom.ChiselItem;
import net.karen.mccoursemod.item.custom.FuelItem;
//import net.karen.mccoursemod.item.custom.MccourseFishingRodItem;
import net.karen.mccoursemod.item.custom.HammerItem;
import net.karen.mccoursemod.item.custom.ModArmorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
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