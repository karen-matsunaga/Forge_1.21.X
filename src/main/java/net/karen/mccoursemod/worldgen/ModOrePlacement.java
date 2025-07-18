package net.karen.mccoursemod.worldgen;

import net.minecraft.world.level.levelgen.placement.*;
import java.util.List;

public class ModOrePlacement {
    // CUSTOM METHOD - Registry all ore placement (NORMAL)
    public static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    // CUSTOM METHOD - Registry all common ore placement (EASY)
    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    // CUSTOM METHOD - Registry all rare ore placement (HARD)
    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}