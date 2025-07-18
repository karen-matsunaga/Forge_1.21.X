package net.karen.mccoursemod.worldgen;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ModConfiguredFeatures {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // ...
    }

    // CUSTOM METHOD - Registry all configured features (JSON file)
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }

    // CUSTOM METHOD - Registry all configured features
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void
            register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                     ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}