package net.karen.mccoursemod.util;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    // Registry all custom block tags
    public static class Blocks {
        // Created tool tags
        public static final TagKey<Block> NEEDS_ALEXANDRITE_TOOL =
                createTag("needs_alexandrite_tool");

        public static final TagKey<Block> INCORRECT_FOR_ALEXANDRITE_TOOL =
                createTag("incorrect_for_alexandrite_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
        }
    }

    // Registry all custom item tags
    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        // Created Fly effect item tag
        public static final TagKey<Item> HELMET_FLY = createTag("helmet_fly");
        public static final TagKey<Item> CHESTPLATE_FLY = createTag("chestplate_fly");
        public static final TagKey<Item> LEGGINGS_FLY = createTag("leggings_fly");
        public static final TagKey<Item> BOOTS_FLY = createTag("boots_fly");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, name));
        }
    }
}