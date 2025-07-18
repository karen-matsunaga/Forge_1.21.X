package net.karen.mccoursemod.worldgen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import java.util.List;

public class ModPlacedFeatures {
    // Registry all custom placed features
    // Alexandrite custom ores
    public static final ResourceKey<PlacedFeature> ALEXANDRITE_ORE_PLACED_KEY =
            registerKey("alexandrite_ore_placed");

    public static final ResourceKey<PlacedFeature> NETHER_ALEXANDRITE_ORE_PLACED_KEY =
            registerKey("nether_alexandrite_ore_placed");

    public static final ResourceKey<PlacedFeature> END_ALEXANDRITE_ORE_PLACED_KEY =
            registerKey("end_alexandrite_ore_placed");

    // Walnut custom tree
    public static final ResourceKey<PlacedFeature> WALNUT_PLACED_KEY = registerKey("walnut_placed");

    // CUSTOM METHOD - Registry all custom placed features
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ALEXANDRITE_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_ALEXANDRITE_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(12,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, NETHER_ALEXANDRITE_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_ALEXANDRITE_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(12,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, END_ALEXANDRITE_ORE_PLACED_KEY,
                 configuredFeatures.getOrThrow(ModConfiguredFeatures.END_ALEXANDRITE_ORE_KEY),
                 ModOrePlacement.commonOrePlacement(12,
                 HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        // Custom tree
        register(context, WALNUT_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WALNUT_KEY),
                 VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                                                    ModBlocks.WALNUT_SAPLING.get()));
    }

    // CUSTOM METHOD - Registry all custom placed features (JSON file)
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // CUSTOM METHOD - Registry all custom placed features
    private static void register(BootstrapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}