package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MccourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // CUSTOM Loot Table
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                              List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new,
                                                                             LootContextParamSets.BLOCK)), lookupProvider));

        // CUSTOM Recipe
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        // CUSTOM Item Block
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
                                                                      generator.addProvider(event.includeServer(), blockTagsProvider);

        // CUSTOM Item Tag
        generator.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider,
                                                                            blockTagsProvider.contentsGetter(), existingFileHelper));

        // CUSTOM Item Model
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

        // CUSTOM Block State
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        // CUSTOM Trim
        generator.addProvider(event.includeServer(), new ModDatapackEntries(packOutput, lookupProvider));

        // CUSTOM Vanilla Loot Table Modifier
        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(packOutput, lookupProvider));

        // CUSTOM Language
        generator.addProvider(event.includeClient(), new ModLanguageProvider(packOutput, "en_us"));

        // CUSTOM Enchantment
//        generator.addProvider(event.includeServer(), new ModEnchantmentTagProvider(packOutput, lookupProvider));
    }
}