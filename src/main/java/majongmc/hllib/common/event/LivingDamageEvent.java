package majongmc.hllib.common.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class LivingDamageEvent extends LivingEvent{
	private final DamageSource source;
    private float amount;
    public LivingDamageEvent(LivingEntity entity, DamageSource source, float amount)
    {
        super(entity);
        this.source = source;
        this.amount = amount;
    }

    public DamageSource getSource() { return source; }

    public float getAmount() { return amount; }
    /**
     * <p>注意：该方法目前在Fabric上无效</p>
     * */
    public void setAmount(float amount) { this.amount = amount; }
}
