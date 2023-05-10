package majongmc.hllib.client.animation;

import org.joml.Vector3f;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
/**
 * <p>对1.19新动画系统的拓展，将其适用范围由HierarchicalModel扩展到所有实体模型</p>
 * <p>该动画系统的用法可参考监守者相关代码{@link net.minecraft.client.model.WardenModel}</p>
 * */
@OnlyIn(Dist.CLIENT)
public class AnimatePlayer {
	private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
	/**
	 * <p>相当于HierarchicalModel中的animate方法，但需要额外输入root参数（实体模型构造方法输入参数的那个ModelPart root，可自己建立一个成员变量将其保存下来在这里使用）</p>
	 * */
	public static void animate(ModelPart root,AnimationState state, AnimationDefinition def, float ageInTicks) {
	      animate(root,state, def, ageInTicks, 1.0F);
	}
	/**
	 * <p>增加了速度参数，可指定动画播放速度</p>
	 * */
	public static void animate(ModelPart root,AnimationState state, AnimationDefinition def, float ageInTicks, float speed) {
		  state.updateTime(ageInTicks, speed);
		  state.ifStarted((p_233392_) -> {
	         KeyframeAnimations.animate(root,def, p_233392_.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE);
	      });
	}
}
