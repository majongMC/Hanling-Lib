package majongmc.hllib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
@Mixin(Mob.class)
public interface MobAccessor {
	@Accessor("goalSelector")
    public GoalSelector getgoalSelector();
	@Accessor("targetSelector")
    public GoalSelector gettargetSelector();
}
