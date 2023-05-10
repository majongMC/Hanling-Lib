package majongmc.hllib.common.event;

import net.minecraft.world.entity.LivingEntity;
/**
 * 注意：相对于Forge没有getTargetType()
 */
public class LivingSetAttackTargetEvent extends LivingEvent{

    private final LivingEntity originalTarget;
    
    public LivingSetAttackTargetEvent(LivingEntity entity, LivingEntity target)
    {
        super(entity);      
        this.originalTarget = target;
    }
    public LivingEntity getTarget()
    {
        return originalTarget;
    }

}
