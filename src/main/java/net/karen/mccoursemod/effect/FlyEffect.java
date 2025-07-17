package net.karen.mccoursemod.effect;

import net.karen.mccoursemod.event.ModEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import org.jetbrains.annotations.NotNull;

public class FlyEffect extends MobEffect {
    protected FlyEffect(MobEffectCategory category, int color) { super(category, color); }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) { // Fly effect APPLIED
        event(entity, 0.05f + (0.02f * amplifier));
        return super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) { return true; }

    private void event(LivingEntity entity, float fly) {
        if (entity instanceof Player player) {
            ModEvents.flyEffect(new TickEvent.PlayerTickEvent.Post(player));
            player.getAbilities().setFlyingSpeed(fly); // Flying speed
        }
    }
}