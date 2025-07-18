package net.karen.mccoursemod.worldgen;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        // ...
    }

    // CUSTOM METHOD - Registry all custom biome modifiers (JSON file)
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
                                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
    }
}