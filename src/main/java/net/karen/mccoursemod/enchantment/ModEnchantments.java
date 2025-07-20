package net.karen.mccoursemod.enchantment;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;

public class ModEnchantments {
    // Registry all custom enchantments -> enchantment name on JSON file
    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER =
            ResourceKey.create(Registries.ENCHANTMENT,
                               ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "lightning_striker"));

    // CUSTOM METHOD - Registry all custom enchantments (JSON file)
    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, LIGHTNING_STRIKER,
                 Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                                                5, 10,
                                                                Enchantment.dynamicCost(5, 8),
                                                                Enchantment.dynamicCost(25, 8),
                                                                2, EquipmentSlotGroup.MAINHAND))
                            .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                            .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                                        EnchantmentTarget.VICTIM, new LightningStrikerEnchantmentEffect(
                                                                  LevelBasedValue.perLevel(0.5F, 0.15F))));

    }

    // CUSTOM METHOD - Registry all custom enchantments
    private static void register(BootstrapContext<Enchantment> registry,
                                 ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}