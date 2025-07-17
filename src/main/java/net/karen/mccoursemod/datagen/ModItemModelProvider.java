package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        // Custom fishing rod
        fishingRodWithCastOverride(ModItems.MCCOURSE_FISHING_ROD);
    }

    // CUSTOM METHOD - Custom Fishing Rod item model
    private void fishingRodWithCastOverride(RegistryObject<Item> item) {
        String itemName = null;
        if (item.getId() != null) { itemName = item.getId().getPath(); }
        // Example: Mccourse Fishing Rod
        getBuilder(itemName).parent(new ModelFile.UncheckedModelFile("minecraft:item/handheld_rod"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "item/" + itemName))
                .override().predicate(ResourceLocation.withDefaultNamespace("cast"), 1.0f)
                .model(new ModelFile.UncheckedModelFile(
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "item/" + itemName + "_cast")))
                .end();
        // Example: Mccourse Fishing Rod Cast
        getBuilder(itemName + "_cast").parent(new ModelFile.UncheckedModelFile("minecraft:item/fishing_rod"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "item/" + itemName + "_cast"));
    }
}