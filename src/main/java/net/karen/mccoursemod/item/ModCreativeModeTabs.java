package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MccourseMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ALEXANDRITE_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("alexandrite_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ALEXANDRITE.get()))
                    .title(Component.translatable("creativetab.mccoursemod.alexandrite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // Custom item
                        output.accept(ModItems.ALEXANDRITE.get());
                        output.accept(ModItems.RAW_ALEXANDRITE.get());

                        // Custom advanced item
                        output.accept(ModItems.CHISEL.get());

                        // Custom food
                        output.accept(ModItems.KOHLRABI.get());

                        // Custom fuel
                        output.accept(ModItems.AURORA_ASHES.get());

                        // Custom tools
                        output.accept(ModItems.ALEXANDRITE_SWORD.get());
                        output.accept(ModItems.ALEXANDRITE_PICKAXE.get());
                        output.accept(ModItems.ALEXANDRITE_SHOVEL.get());
                        output.accept(ModItems.ALEXANDRITE_AXE.get());
                        output.accept(ModItems.ALEXANDRITE_HOE.get());
                        output.accept(ModItems.ALEXANDRITE_HAMMER.get());
                        output.accept(ModItems.ALEXANDRITE_HORSE_ARMOR.get());

                        // Custom armors
                        output.accept(ModItems.ALEXANDRITE_HELMET.get());
                        output.accept(ModItems.ALEXANDRITE_CHESTPLATE.get());
                        output.accept(ModItems.ALEXANDRITE_LEGGINGS.get());
                        output.accept(ModItems.ALEXANDRITE_BOOTS.get());

                        // Custom Smithing Template
                        output.accept(ModItems.KAUPEN_SMITHING_TEMPLATE.get());

                        // Custom Bow
                        output.accept(ModItems.KAUPEN_BOW.get());

                        // Custom disc
                        output.accept(ModItems.BAR_BRAWL_MUSIC_DISC.get());

                        // Custom crop
                        output.accept(ModItems.KOHLRABI_SEEDS.get());

                        // Custom bush crop
                        output.accept(ModItems.HONEY_BERRIES.get());

                        // Custom spawn egg
                        output.accept(ModItems.TRICERATOPS_SPAWN_EGG.get());

                        // Custom Throwable Projectiles
                        output.accept(ModItems.TOMAHAWK.get());

                        // Custom animated texture
                        output.accept(ModItems.RADIATION_STAFF.get());

                        // Custom Mccourse Fishing Rod
                        output.accept(ModItems.MCCOURSE_FISHING_ROD.get());

                        // Level Charger items
                        output.accept(ModItems.LEVEL_CHARGER_PLUS.get());
                        output.accept(ModItems.LEVEL_CHARGER_MINUS.get());
                    }).build());

    public static final RegistryObject<CreativeModeTab> ALEXANDRITE_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("alexandrite_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ALEXANDRITE_BLOCK.get()))
                    .withTabsBefore(ALEXANDRITE_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.mccoursemod.alexandrite_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // Custom block
                        output.accept(ModBlocks.ALEXANDRITE_BLOCK.get());
                        output.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());

                        // Custom ore
                        output.accept(ModBlocks.ALEXANDRITE_ORE.get());
                        output.accept(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get());
                        output.accept(ModBlocks.ALEXANDRITE_END_ORE.get());
                        output.accept(ModBlocks.ALEXANDRITE_NETHER_ORE.get());

                        // Custom advanced block
                        output.accept(ModBlocks.MAGIC_BLOCK.get());

                        // Custom non-block
                        output.accept(ModBlocks.ALEXANDRITE_STAIRS.get()); // Stairs
                        output.accept(ModBlocks.ALEXANDRITE_SLAB.get()); // Slab
                        output.accept(ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get()); // Pressure Plate
                        output.accept(ModBlocks.ALEXANDRITE_BUTTON.get()); // Button
                        output.accept(ModBlocks.ALEXANDRITE_FENCE.get()); // Fence
                        output.accept(ModBlocks.ALEXANDRITE_FENCE_GATE.get()); // Fence Gate
                        output.accept(ModBlocks.ALEXANDRITE_WALL.get()); // Wall
                        output.accept(ModBlocks.ALEXANDRITE_DOOR.get()); // Door
                        output.accept(ModBlocks.ALEXANDRITE_TRAPDOOR.get()); // Trapdoor

                        // Custom lamp
                        output.accept(ModBlocks.ALEXANDRITE_LAMP.get());

                        // Custom crop block
                        output.accept(ModBlocks.KOHLRABI_CROP.get());

                        // Custom bush crop block
                        output.accept(ModBlocks.HONEY_BERRY_BUSH.get());

                        // Custom tree
                        output.accept(ModBlocks.WALNUT_LOG.get());
                        output.accept(ModBlocks.WALNUT_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_WALNUT_LOG.get());
                        output.accept(ModBlocks.STRIPPED_WALNUT_WOOD.get());
                        output.accept(ModBlocks.WALNUT_PLANKS.get());
                        output.accept(ModBlocks.WALNUT_SAPLING.get());
                        output.accept(ModBlocks.WALNUT_LEAVES.get());

                        // Custom block model
                        output.accept(ModBlocks.CHAIR.get());

                        // Custom block entity
                        output.accept(ModBlocks.PEDESTAL.get());
                        output.accept(ModBlocks.GROWTH_CHAMBER.get());

                        // Custom Enchant block
                        output.accept(ModBlocks.DISENCHANT.get());
                        output.accept(ModBlocks.ENCHANT.get());
                    }).build());

    // CUSTOM METHOD - Registry all blocks and items on event
    public static void register(IEventBus eventBus) { CREATIVE_MODE_TABS.register(eventBus); }
}