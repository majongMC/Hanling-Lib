package majongmc.hllib.common.event;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class LivingEntityUseItemEvent extends LivingEvent{
	private final ItemStack item;
    private int duration;

    private LivingEntityUseItemEvent(LivingEntity entity, @NotNull ItemStack item, int duration)
    {
        super(entity);
        this.item = item;
        this.setDuration(duration);
    }

    @NotNull
    public ItemStack getItem()
    {
        return item;
    }

    public int getDuration()
    {
        return duration;
    }
    /**
     * <p>注意：该方法目前在Fabric上无效</p>
     * */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    public static class Start extends LivingEntityUseItemEvent
    {
        public Start(LivingEntity entity, @NotNull ItemStack item, int duration)
        {
            super(entity, item, duration);
        }
    }
    /**
     * <p>注意：该事件目前在Fabric上无法取消</p>
     * */
    public static class Stop extends LivingEntityUseItemEvent
    {
        public Stop(LivingEntity entity, @NotNull ItemStack item, int duration)
        {
            super(entity, item, duration);
        }
    }
}
