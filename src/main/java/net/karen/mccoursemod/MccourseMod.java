package net.karen.mccoursemod;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.effect.ModEffects;
import net.karen.mccoursemod.enchantment.ModEnchantmentEffects;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.entity.client.ChairRenderer;
import net.karen.mccoursemod.entity.client.TomahawkProjectileRenderer;
import net.karen.mccoursemod.entity.client.TriceratopsRenderer;
import net.karen.mccoursemod.item.ModCreativeModeTabs;
import net.karen.mccoursemod.item.ModItemProperties;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.particle.AlexandriteParticles;
import net.karen.mccoursemod.particle.ModParticles;
import net.karen.mccoursemod.potion.ModPotions;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.villager.ModVillagers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
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
        ModDataComponentTypes.register(modEventBus); // Registry all custom data components
        ModSounds.register(modEventBus); // Registry all custom sounds
        ModEffects.register(modEventBus); // Registry all custom effects
        ModPotions.register(modEventBus); // Registry all custom potions
        ModEnchantmentEffects.register(modEventBus); // Registry all custom enchantments
        ModEntities.register(modEventBus); // Registry all custom entities
        ModVillagers.register(modEventBus); // Registry all custom villagers
        ModParticles.register(modEventBus); // Registry all custom particles

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Registry all custom block crop and item crop
            ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModItems.KOHLRABI_SEEDS.get(), 0.15f);
        });
    }

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
        // CUSTOM EVENT - Register all custom entity renderers
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Registry all custom item properties
            ModItemProperties.addCustomItemProperties();
            // Registry all custom entity renderers
            EntityRenderers.register(ModEntities.TRICERATOPS.get(), TriceratopsRenderer::new);
            // Registry all custom throwable projectiles
            EntityRenderers.register(ModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
            // Registry all custom sittable blocks
            EntityRenderers.register(ModEntities.CHAIR.get(), ChairRenderer::new);
        }
        // CUSTOM EVENT - Register all custom particles
        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            // Register all custom particles
            event.registerSpriteSet(ModParticles.ALEXANDRITE_PARTICLES.get(), AlexandriteParticles.Provider::new);
        }
    }
}