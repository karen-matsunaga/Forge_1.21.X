package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MccourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ALEXANDRITE.get());
        basicItem(ModItems.RAW_ALEXANDRITE.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.KOHLRABI.get());
        basicItem(ModItems.AURORA_ASHES.get());

        buttonItem(ModBlocks.ALEXANDRITE_BUTTON, ModBlocks.ALEXANDRITE_BLOCK);
        fenceItem(ModBlocks.ALEXANDRITE_FENCE, ModBlocks.ALEXANDRITE_BLOCK);
        wallItem(ModBlocks.ALEXANDRITE_WALL, ModBlocks.ALEXANDRITE_BLOCK);
        simpleBlockItem(ModBlocks.ALEXANDRITE_DOOR);

//        // Custom fishing rod
//        fishingRodWithCastOverride(ModItems.MCCOURSE_FISHING_ROD);
    }

    // CUSTOM METHOD - Custom Button item model
    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    // CUSTOM METHOD - Custom Fence item model
    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    // CUSTOM METHOD - Custom Wall item model
    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    // CUSTOM METHOD - Custom Door item model
    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    // CUSTOM METHOD - Custom Fishing Rod item model
//    private void fishingRodWithCastOverride(RegistryObject<? extends Item> item) {
//        ResourceLocation itemName = item.getId();
//        if (itemName != null) {
//            String itemId = item.getId().getPath();
//            // Example: Mccourse Fishing Rod
//            getBuilder(itemId).parent(new ModelFile.UncheckedModelFile("minecraft:item/handheld_rod"))
//                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "item/" + itemId))
//                    .override().predicate(ResourceLocation.withDefaultNamespace("cast"), 1.0f)
//                    .model(new ModelFile.UncheckedModelFile(
//                            ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "item/" + itemId + "_cast")))
//                    .end();
//            // Example: Mccourse Fishing Rod Cast
//            getBuilder(itemId + "_cast").parent(new ModelFile.UncheckedModelFile("minecraft:item/fishing_rod"))
//                                               .texture("layer0",
//                                                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
//                                                                                        "item/" + itemId + "_cast"));
//        }
//    }
}