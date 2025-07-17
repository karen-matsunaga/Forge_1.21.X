package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.custom.ChiselItem;
import net.karen.mccoursemod.item.custom.FuelItem;
import net.karen.mccoursemod.item.custom.MccourseFishingRodItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MccourseMod.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> KOHLRABI = ITEMS.register("kohlrabi",
            () -> new Item(new Item.Properties().food(ModFoodProperties.KOHLRABI)) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context,
                                            @NotNull List<Component> list, @NotNull TooltipFlag flag) {
                    list.add(Component.translatable("tooltip.mccoursemod.kohlrabi"));
                    super.appendHoverText(stack, context, list, flag);
                }
            });
    public static final RegistryObject<Item> AURORA_ASHES = ITEMS.register("aurora_ashes",
            () -> new FuelItem(new Item.Properties(), 1200));

    // Custom fishing rod
    public static final RegistryObject<Item> MCCOURSE_FISHING_ROD = item("mccourse_fishing_rod",
            () -> new MccourseFishingRodItem(new Item.Properties().fireResistant()));

    // CUSTOM METHOD - Registry all custom items
    public static RegistryObject<Item> item(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }

    // CUSTOM METHOD - Registry all items on MccourseMod file
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}