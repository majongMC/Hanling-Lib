package majongmc.hllib.common.event;

import net.minecraft.world.entity.LivingEntity;

public class LivingEvent extends EntityEvent{
	private final LivingEntity livingEntity;

    public LivingEvent(LivingEntity entity)
    {
        super(entity);
        livingEntity = entity;
    }

    public LivingEntity getEntity()
    {
        return livingEntity;
    }
    public static class LivingTickEvent extends LivingEvent
    {
        public LivingTickEvent(LivingEntity e){ super(e); }
    }
}
