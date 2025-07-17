package net.karen.mccoursemod;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.effect.ModEffects;
import net.karen.mccoursemod.item.ModCreativeModeTabs;
//import net.karen.mccoursemod.item.ModItemProperties;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.potion.ModPotions;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MccourseMod.MOD_ID)
public class MccourseMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "mccoursemod";
    // Directly reference a slf4j logger
    // private static final Logger LOGGER = LogUtils.getLogger();

    public MccourseMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for mod-loading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register all id
        ModCreativeModeTabs.register(modEventBus); // Registry all creative mode tabs
        ModItems.register(modEventBus); // Registry all custom items
        ModBlocks.register(modEventBus); // Registry all custom blocks
        ModPotions.register(modEventBus); // Registry all custom potions
        ModEffects.register(modEventBus); // Registry all custom effects
        ModDataComponentTypes.register(modEventBus); // Registry all custom data components
//        ModItemProperties.addCustomItemProperties(); // Registry all custom item properties

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) { // Ingredients tab
            event.accept(ModItems.ALEXANDRITE);
            event.accept(ModItems.RAW_ALEXANDRITE);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) { // Building blocks tab
            event.accept(ModBlocks.ALEXANDRITE_BLOCK);
            event.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {}
    }
}