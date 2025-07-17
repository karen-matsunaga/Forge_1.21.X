package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    // Registry all vanilla trims and custom trims
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static { trimMaterials.put(TrimMaterials.QUARTZ, 0.1F); trimMaterials.put(TrimMaterials.IRON, 0.2F);
             trimMaterials.put(TrimMaterials.NETHERITE, 0.3F); trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
             trimMaterials.put(TrimMaterials.COPPER, 0.5F); trimMaterials.put(TrimMaterials.GOLD, 0.6F);
             trimMaterials.put(TrimMaterials.EMERALD, 0.7F); trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
             trimMaterials.put(TrimMaterials.LAPIS, 0.9F); trimMaterials.put(TrimMaterials.AMETHYST, 1.0F); }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MccourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Custom item
        basicItem(ModItems.ALEXANDRITE.get());
        basicItem(ModItems.RAW_ALEXANDRITE.get());

        // Custom advanced item
        basicItem(ModItems.CHISEL.get());

        // Custom food
        basicItem(ModItems.KOHLRABI.get());

        // Custom fuel
        basicItem(ModItems.AURORA_ASHES.get());

        // Custom button
        buttonItem(ModBlocks.ALEXANDRITE_BUTTON, ModBlocks.ALEXANDRITE_BLOCK);

        // Custom fence
        fenceItem(ModBlocks.ALEXANDRITE_FENCE, ModBlocks.ALEXANDRITE_BLOCK);

        // Custom wall
        wallItem(ModBlocks.ALEXANDRITE_WALL, ModBlocks.ALEXANDRITE_BLOCK);

        // Custom door
        simpleBlockItem(ModBlocks.ALEXANDRITE_DOOR);

        // Custom hand held item
        handheldItem(ModItems.ALEXANDRITE_SWORD);
        handheldItem(ModItems.ALEXANDRITE_PICKAXE);
        handheldItem(ModItems.ALEXANDRITE_SHOVEL);
        handheldItem(ModItems.ALEXANDRITE_AXE);
        handheldItem(ModItems.ALEXANDRITE_HOE);
        handheldItem(ModItems.ALEXANDRITE_HAMMER);

        // Custom trimmed armor item model
        trimmedArmorItem(ModItems.ALEXANDRITE_HELMET);
        trimmedArmorItem(ModItems.ALEXANDRITE_CHESTPLATE);
        trimmedArmorItem(ModItems.ALEXANDRITE_LEGGINGS);
        trimmedArmorItem(ModItems.ALEXANDRITE_BOOTS);

        // Custom horse armor
        basicItem(ModItems.ALEXANDRITE_HORSE_ARMOR.get());

//        // Custom fishing rod
//        fishingRodWithCastOverride(ModItems.MCCOURSE_FISHING_ROD);
    }

    // CUSTOM METHOD - Custom trimmed armor item model -> Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = MccourseMod.MOD_ID; // Change this to your mod id
        if (itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;
                String armorType = switch (armorItem.getEquipmentSlot()) {
                                          case HEAD -> "helmet"; case CHEST -> "chestplate";
                                          case LEGS -> "leggings"; case FEET -> "boots"; default -> ""; },
                       armorItemPath = armorItem.toString(),
                       trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath(),
                       currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath),
                                 trimResLoc = ResourceLocation.parse(trimPath), // minecraft namespace
                                 trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                          .parent(new ModelFile.UncheckedModelFile("item/generated"))
                          .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                          .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(), mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace() +
                                                                        ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    // CUSTOM METHOD - Custom Hand Held item (MAIN HAND)
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,"item/" + item.getId().getPath()));
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