package majongmc.hllib.common.event;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.HitResult;
/**
 * <p>注意：本事件采用了与Forge不同的注入点（Forge的注入点太骚，难以实现），效果可能有细微区别，且该事件无法取消</p>
 * */
public class ProjectileImpactEvent extends EntityEvent{
	private final HitResult ray;
    private final Projectile projectile;

    public ProjectileImpactEvent(Projectile projectile, HitResult ray)
    {
        super(projectile);
        this.ray = ray;
        this.projectile = projectile;
    }

    public HitResult getRayTraceResult()
    {
        return ray;
    }

    public Projectile getProjectile()
    {
        return projectile;
    }
}
