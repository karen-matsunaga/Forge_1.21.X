package net.karen.mccoursemod.trim;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import java.util.Map;

public class ModTrimMaterials {
    // CUSTOM METHOD - Registry all custom trim material texture
    public static final ResourceKey<TrimMaterial> ALEXANDRITE =
           ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "alexandrite"));

    // CUSTOM METHOD - Registry all custom trim material color
    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, ALEXANDRITE, ModItems.ALEXANDRITE.get(),
                 Style.EMPTY.withColor(TextColor.parseColor("#031cfc").getOrThrow()), 0.8F);
    }

    // CUSTOM METHOD - Registry all custom trim materials
    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Item item,
                                 Style style, float itemModelIndex) {
        TrimMaterial trimmaterial = TrimMaterial.create(trimKey.location().getPath(), item, itemModelIndex,
        Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(style), Map.of());
        context.register(trimKey, trimmaterial);
    }
}