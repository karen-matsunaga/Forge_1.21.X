package net.karen.mccoursemod.screen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.screen.custom.GrowthChamberMenu;
import net.karen.mccoursemod.screen.custom.PedestalMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, MccourseMod.MOD_ID);

    public static final RegistryObject<MenuType<PedestalMenu>> PEDESTAL_MENU =
            MENUS.register("pedestal_name", () -> IForgeMenuType.create(PedestalMenu::new));

    public static final RegistryObject<MenuType<GrowthChamberMenu>> GROWTH_CHAMBER_MENU =
            MENUS.register("growth_chamber_menu", () -> IForgeMenuType.create(GrowthChamberMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}