package majongmc.hllib.common.event;

import net.minecraft.world.entity.Entity;

public class EntityEvent extends Event{
	private final Entity entity;

    public EntityEvent(Entity entity)
    {
        this.entity = entity;
    }

    public Entity getEntity()
    {
        return entity;
    }
}
