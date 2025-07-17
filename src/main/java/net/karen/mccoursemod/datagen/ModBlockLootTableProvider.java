package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.KohlrabiCropBlock;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        // Custom block
        dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());

        // Custom advanced block
        // dropSelf(ModBlocks.MAGIC_BLOCK.get());

        // Custom ore
        this.add(ModBlocks.ALEXANDRITE_ORE.get(),
                block -> createOreDrop(ModBlocks.ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));

        this.add(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get(),
                ModItems.RAW_ALEXANDRITE.get(), 2, 6));

        // Custom stair
        dropSelf(ModBlocks.ALEXANDRITE_STAIRS.get());

        // Custom slab
        this.add(ModBlocks.ALEXANDRITE_SLAB.get(), block -> createSlabItemTable(ModBlocks.ALEXANDRITE_SLAB.get()));

        // Custom pressure plate
        dropSelf(ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get());

        // Custom button
        dropSelf(ModBlocks.ALEXANDRITE_BUTTON.get());

        // Custom fence
        dropSelf(ModBlocks.ALEXANDRITE_FENCE.get());

        // Custom fence gate
        dropSelf(ModBlocks.ALEXANDRITE_FENCE_GATE.get());

        // Custom wall
        dropSelf(ModBlocks.ALEXANDRITE_WALL.get());

        // Custom trapdoor
        dropSelf(ModBlocks.ALEXANDRITE_TRAPDOOR.get());

        // Custom door
        this.add(ModBlocks.ALEXANDRITE_DOOR.get(), block -> createDoorTable(ModBlocks.ALEXANDRITE_DOOR.get()));

        // Custom lamp
        dropSelf(ModBlocks.ALEXANDRITE_LAMP.get());

        // Custom block crop and item crop
        LootItemCondition.Builder lootItemConditionBuilder =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.KOHLRABI_CROP.get())
                                                   .setProperties(StatePropertiesPredicate.Builder.properties()
                                                   .hasProperty(KohlrabiCropBlock.AGE, KohlrabiCropBlock.MAX_AGE));

        this.add(ModBlocks.KOHLRABI_CROP.get(), this.createCropDrops(ModBlocks.KOHLRABI_CROP.get(),
                ModItems.KOHLRABI.get(), ModItems.KOHLRABI_SEEDS.get(), lootItemConditionBuilder));

        // Custom bush block item crop
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.add(ModBlocks.HONEY_BERRY_BUSH.get(), block -> this.applyExplosionDecay(block,
                 LootTable.lootTable().withPool(LootPool.lootPool()
                          .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH.get())
                          .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3)))
                          .add(LootItem.lootTableItem(ModItems.HONEY_BERRIES.get()))
                          .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                          .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
                          .withPool(LootPool.lootPool()
                          .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH.get())
                          .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2)))
                          .add(LootItem.lootTableItem(ModItems.HONEY_BERRIES.get()))
                          .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                          .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                 )));
    }

    // CUSTOM METHOD - Custom ore loot table
    protected LootTable.Builder createMultipleOreDrops(Block block, Item item,
                                                       float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
               LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                           .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
        );
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}