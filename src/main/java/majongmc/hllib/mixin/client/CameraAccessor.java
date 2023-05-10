package majongmc.hllib.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.Camera;

@Mixin(Camera.class)
public interface CameraAccessor {
	@Accessor("xRot")
    public void setxRot(float xRot);
	@Accessor("yRot")
    public void setyRot(float yRot);
}
