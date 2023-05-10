package majongmc.hllib.common.iforgeport;

import net.minecraft.nbt.CompoundTag;
/**
 * <p>已经通过接口注入注入到原版Entity类，可直接调用，效果与Forge中的getPersistentData方法相同</p>
 * <p>注意：为避免与其他模组冲突，方法后面相比于forge原版方法多了一个_h,自己加上就行</p>
 * <p>如果你在导入api的时候接口注入没有生效，可使用MiniIForgeEntityApi中的方法，效果相同</p>
 * */
public interface MiniIForgeEntityInject {
	default CompoundTag getPersistentData_h() {
		return null;
	}
}
