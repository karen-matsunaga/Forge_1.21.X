package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.custom.AlexandriteLampBlock;
import net.karen.mccoursemod.block.custom.HoneyBerryBushBlock;
import net.karen.mccoursemod.block.custom.KohlrabiCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MccourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Custom block
        blockWithItem(ModBlocks.ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK);

        blockWithItem(ModBlocks.ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.ALEXANDRITE_NETHER_ORE);
        blockWithItem(ModBlocks.ALEXANDRITE_END_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);

        // Custom stairs
        stairsBlock(ModBlocks.ALEXANDRITE_STAIRS.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        // Custom slab
        slabBlock(ModBlocks.ALEXANDRITE_SLAB.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()),
                                                    blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        // Custom button
        buttonBlock(ModBlocks.ALEXANDRITE_BUTTON.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        // Custom pressure plate
        pressurePlateBlock(ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        // Custom fence
        fenceBlock(ModBlocks.ALEXANDRITE_FENCE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        // Custom fence gate
        fenceGateBlock(ModBlocks.ALEXANDRITE_FENCE_GATE.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        // Custom wall
        wallBlock(ModBlocks.ALEXANDRITE_WALL.get(), blockTexture(ModBlocks.ALEXANDRITE_BLOCK.get()));

        // Custom door
        doorBlockWithRenderType(ModBlocks.ALEXANDRITE_DOOR.get(), modLoc("block/alexandrite_door_bottom"),
                                                                  modLoc("block/alexandrite_door_top"), "cutout");

        // Custom trapdoor
        trapdoorBlockWithRenderType(ModBlocks.ALEXANDRITE_TRAPDOOR.get(), modLoc("block/alexandrite_trapdoor"),
                                                                 true, "cutout");

        // Custom stairs
        blockItem(ModBlocks.ALEXANDRITE_STAIRS);

        // Custom slab
        blockItem(ModBlocks.ALEXANDRITE_SLAB);

        // Custom pressure plate
        blockItem(ModBlocks.ALEXANDRITE_PRESSURE_PLATE);

        // Custom fence gate
        blockItem(ModBlocks.ALEXANDRITE_FENCE_GATE);

        // Custom trapdoor
        blockItem(ModBlocks.ALEXANDRITE_TRAPDOOR, "_bottom");

        // Custom lamp
        customLamp();

        // Custom block crop
        makeCrop(((CropBlock) ModBlocks.KOHLRABI_CROP.get()), "kohlrabi_crop_stage", "kohlrabi_crop_stage");

        // Custom bush block crop
        makeBush(((SweetBerryBushBlock) ModBlocks.HONEY_BERRY_BUSH.get()),
                "honey_berry_bush_stage", "honey_berry_bush_stage");

        // Custom tree
        logBlock(ModBlocks.WALNUT_LOG.get());
        axisBlock(ModBlocks.WALNUT_WOOD.get(), blockTexture(ModBlocks.WALNUT_LOG.get()), blockTexture(ModBlocks.WALNUT_LOG.get()));
        logBlock(ModBlocks.STRIPPED_WALNUT_LOG.get());
        axisBlock(ModBlocks.STRIPPED_WALNUT_WOOD.get(), blockTexture(ModBlocks.STRIPPED_WALNUT_LOG.get()), blockTexture(ModBlocks.STRIPPED_WALNUT_LOG.get()));

        blockItem(ModBlocks.WALNUT_LOG);
        blockItem(ModBlocks.WALNUT_WOOD);
        blockItem(ModBlocks.STRIPPED_WALNUT_LOG);
        blockItem(ModBlocks.STRIPPED_WALNUT_WOOD);

        blockWithItem(ModBlocks.WALNUT_PLANKS);

        leavesBlock(ModBlocks.WALNUT_LEAVES);
        saplingBlock(ModBlocks.WALNUT_SAPLING);
    }

    // CUSTOM METHOD - Block with Cube format
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    // CUSTOM METHOD - Block item -> stairs, slab, pressure plate and fence gate
    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("mccoursemod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    // CUSTOM METHOD - Block item -> trapdoor item
    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("mccoursemod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }

    // CUSTOM METHOD - Custom Lamp block
    private void customLamp() {
        getVariantBuilder(ModBlocks.ALEXANDRITE_LAMP.get()).forAllStates(state -> {
            if (state.getValue(AlexandriteLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("alexandrite_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "block/" + "alexandrite_lamp_on")))};
            }
            else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("alexandrite_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "block/" + "alexandrite_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.ALEXANDRITE_LAMP.get(), models().cubeAll("alexandrite_lamp_on",
                ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "block/" + "alexandrite_lamp_on")));
    }

    // CUSTOM METHOD - Custom block crop
    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    // CUSTOM COMPLEMENT METHOD - Custom block crop
    private ConfiguredModel[] states(BlockState state, CropBlock block,
                                     String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((KohlrabiCropBlock) block).getAgeProperty()),
                    ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "block/" + textureName +
                                                          state.getValue(((KohlrabiCropBlock) block).getAgeProperty())))
                                                               .renderType("cutout"));
        return models;
    }

    // CUSTOM METHOD - Custom bush block crop
    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    // CUSTOM COMPLEMENT METHOD - Custom bush block crop
    private ConfiguredModel[] states(BlockState state,
                                     String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(HoneyBerryBushBlock.AGE),
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "block/" + textureName +
                                                              state.getValue(HoneyBerryBushBlock.AGE))).renderType("cutout"));

        return models;
    }

    // CUSTOM METHOD - Custom sapling block tree
    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                                                             blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    // CUSTOM METHOD - Custom leave block tree
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                                                                     ResourceLocation.parse("minecraft:block/leaves"),
                                                           "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}