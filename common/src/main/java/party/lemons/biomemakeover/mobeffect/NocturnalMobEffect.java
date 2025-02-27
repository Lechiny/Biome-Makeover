package party.lemons.biomemakeover.mobeffect;

import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class NocturnalMobEffect extends BMMobEffect {
    public NocturnalMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xba93c7);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        super.applyEffectTick(livingEntity, i);

        if(!livingEntity.level.isClientSide() && livingEntity instanceof Player player)
        {
            player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
        }
    }
}
