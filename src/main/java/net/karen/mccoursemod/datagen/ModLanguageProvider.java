package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, MccourseMod.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        // CUSTOM Blocks
        addBlock(ModBlocks.ALEXANDRITE_BLOCK, "Block of Alexandrite");
        addBlock(ModBlocks.RAW_ALEXANDRITE_BLOCK, "Block of Raw Alexandrite");
        addBlock(ModBlocks.ALEXANDRITE_ORE, "Alexandrite Ore");
        addBlock(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE, "Alexandrite Deepslate Ore");
        addBlock(ModBlocks.MAGIC_BLOCK, "Magic Block");
        addBlock(ModBlocks.ALEXANDRITE_STAIRS, "Alexandrite Stairs");
        addBlock(ModBlocks.ALEXANDRITE_SLAB, "Alexandrite Slab");
        addBlock(ModBlocks.ALEXANDRITE_BUTTON, "Alexandrite Button");
        addBlock(ModBlocks.ALEXANDRITE_PRESSURE_PLATE, "Alexandrite Pressure Plate");
        addBlock(ModBlocks.ALEXANDRITE_FENCE, "Alexandrite Fence");
        addBlock(ModBlocks.ALEXANDRITE_FENCE_GATE, "Alexandrite Fence Gate");
        addBlock(ModBlocks.ALEXANDRITE_WALL, "Alexandrite Wall");
        addBlock(ModBlocks.ALEXANDRITE_DOOR, "Alexandrite Door");
        addBlock(ModBlocks.ALEXANDRITE_TRAPDOOR, "Alexandrite Trapdoor");
        addBlock(ModBlocks.ALEXANDRITE_LAMP, "Alexandrite Lamp");
        addBlock(ModBlocks.KOHLRABI_CROP, "Kohlrabi Crop");
        addBlock(ModBlocks.HONEY_BERRY_BUSH, "Honey Berry Bush");
        addBlock(ModBlocks.CHAIR, "Chair");
        addBlock(ModBlocks.PEDESTAL, "Pedestal");
        addBlock(ModBlocks.GROWTH_CHAMBER, "Growth Chamber");
        addBlock(ModBlocks.WALNUT_LOG, "Walnut Log");
        addBlock(ModBlocks.WALNUT_PLANKS, "Walnut Planks");
        addBlock(ModBlocks.WALNUT_WOOD, "Walnut Wood");
        addBlock(ModBlocks.STRIPPED_WALNUT_LOG, "Stripped Walnut Log");
        addBlock(ModBlocks.STRIPPED_WALNUT_WOOD, "Stripped Walnut Wood");
        addBlock(ModBlocks.WALNUT_LEAVES, "Walnut Leaves");
        addBlock(ModBlocks.WALNUT_SAPLING, "Walnut Sapling");
        addBlock(ModBlocks.ALEXANDRITE_END_ORE, "Alexandrite End Ore");
        addBlock(ModBlocks.ALEXANDRITE_NETHER_ORE, "Alexandrite Nether Ore");

        // CUSTOM Items
        addItem(ModItems.ALEXANDRITE, "Alexandrite Gem");
        addItem(ModItems.RAW_ALEXANDRITE, "Raw Alexandrite");
        addItem(ModItems.CHISEL, "Chisel");
        addItem(ModItems.KOHLRABI, "Kohlrabi");
        addItem(ModItems.AURORA_ASHES, "Aurora Ashes");
        addItem(ModItems.KOHLRABI_SEEDS, "Kohlrabi Seeds");
        addItem(ModItems.HONEY_BERRIES, "Honey Berries");
        addItem(ModItems.TRICERATOPS_SPAWN_EGG, "Triceratops Spawn Egg");
        addItem(ModItems.TOMAHAWK, "Tomahawk");
        addItem(ModItems.RADIATION_STAFF, "Radiation Staff");
        addItem(ModItems.KAUPEN_SMITHING_TEMPLATE, "Kaupen Armor Trim Smithing Template");
        addItem(ModItems.BAR_BRAWL_MUSIC_DISC, "Bar Brawl Music Disc");
        addItem(ModItems.LEVEL_CHARGER_PLUS, "Level Charger Plus");
        addItem(ModItems.LEVEL_CHARGER_MINUS, "Level Charger Minus");

        // CUSTOM Tools
        addItem(ModItems.ALEXANDRITE_SWORD, "Alexandrite Sword");
        addItem(ModItems.ALEXANDRITE_PICKAXE, "Alexandrite Pickaxe");
        addItem(ModItems.ALEXANDRITE_SHOVEL, "Alexandrite Shovel");
        addItem(ModItems.ALEXANDRITE_AXE, "Alexandrite Axe");
        addItem(ModItems.ALEXANDRITE_HOE, "Alexandrite Hoe");
        addItem(ModItems.ALEXANDRITE_HAMMER, "Alexandrite Hammer");
        addItem(ModItems.MCCOURSE_FISHING_ROD, "Mccourse Fishing Rod");
        addItem(ModItems.KAUPEN_BOW, "Kaupen Bow");

        // CUSTOM Armors
        addItem(ModItems.ALEXANDRITE_HELMET, "Alexandrite Helmet");
        addItem(ModItems.ALEXANDRITE_CHESTPLATE, "Alexandrite Chestplate");
        addItem(ModItems.ALEXANDRITE_LEGGINGS, "Alexandrite Leggings");
        addItem(ModItems.ALEXANDRITE_BOOTS, "Alexandrite Boots");
        addItem(ModItems.ALEXANDRITE_HORSE_ARMOR, "Alexandrite Horse Armor");

        // CUSTOM Enchantments
        add("enchantment.mccoursemod.lightning_striker", "Lightning Striker");

        // CUSTOM Entities
        addEntityType(ModEntities.TRICERATOPS, "Triceratops");
        addEntityType(ModEntities.TOMAHAWK, "Tomahawk");

        // CUSTOM Mobs
        add("entity.minecraft.villager.mccoursemod.kaupenger", "Kaupenger");

        // CUSTOM Desc
        add("item.mccoursemod.bar_brawl_music_disc.desc", "Bryan Tech - Bar Brawl (CC0)");

        // CUSTOM Paintings
        add("painting.mccoursemod.world.title", "World");
        add("painting.mccoursemod.world.author", "NanoAttack");
        add("painting.mccoursemod.shrimp.title", "Shrimp");
        add("painting.mccoursemod.shrimp.author", "NanoAttack");
        add("painting.mccoursemod.saw_them.title", "Saw Them");
        add("painting.mccoursemod.saw_them.author", "NanoAttack");

        // CUSTOM TRIM
        add("trim_pattern.mccoursemod.kaupen", "Kaupen Armor Trim");
        add("trim_material.mccoursemod.alexandrite", "Alexandrite Material");

        // CUSTOM Creative Tabs
        add("creativetab.mccoursemod.alexandrite_items", "Alexandrite Items");
        add("creativetab.mccoursemod.alexandrite_blocks", "Alexandrite Blocks");

        // CUSTOM Tooltip
        add("tooltip.mccoursemod.magic_block.tooltip", "This Block is quite §9MAGICAL§r");
        add("tooltip.mccoursemod.chisel.shift_down", "This Item changes certain Blocks");
        add("tooltip.mccoursemod.chisel", "Press §eShift§r for more Information!");
        add("tooltip.mccoursemod.kohlrabi", "This tastes GREAT!");

        // CUSTOM Effects and Potions
        // SLIMEY effect + potion
        add("effect.mccoursemod.slimey", "Slimey");
        add("item.minecraft.potion.effect.slimey_potion", "Slimey Potion");
        add("item.minecraft.splash_potion.effect.slimey_potion", "Slimey Splash Potion");
        add("item.minecraft.lingering_potion.effect.slimey_potion", "Slimey Lingering Potion");
        add("item.minecraft.tipped_arrow.effect.slimey_potion", "Arrow of Slimey");

        // FLY effect + potion
        add("effect.mccoursemod.fly", "Fly");
        add("item.minecraft.potion.effect.fly_potion", "Fly Potion");
        add("item.minecraft.splash_potion.effect.fly_potion", "Fly Splash Potion");
        add("item.minecraft.lingering_potion.effect.fly_potion", "Fly Lingering Potion");
        add("item.minecraft.tipped_arrow.effect.fly_potion", "Arrow of Fly");

        // NOTHING effect + potion
        add("effect.mccoursemod.nothing", "Nothing");
        add("item.minecraft.potion.effect.nothing_potion", "Nothing Potion");
        add("item.minecraft.splash_potion.effect.nothing_potion", "Nothing Splash Potion");
        add("item.minecraft.lingering_potion.effect.nothing_potion", "Nothing Lingering Potion");
        add("item.minecraft.tipped_arrow.effect.nothing_potion", "Arrow of Nothing");
    }
}