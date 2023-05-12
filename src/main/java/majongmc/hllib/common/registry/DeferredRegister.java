package majongmc.hllib.common.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import majongmc.hllib.common.event.EventBus;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
/**
 * <p>移植了Forge的延迟注册器</p>
 * <p>注意：虽然是移植的Forge的延迟注册器，但不是真的延迟注册，在主类中调用Register时就会注册</p>
 * <p>因此需要注意你的注册顺序，建议的注册顺序为：实体->方块->物品</p>
 * */
public class DeferredRegister<T> {
	private String modid;
	private Registry<T> registry;
	private List<RegistryObject<T>> objlist=new ArrayList<>();
	private DeferredRegister(Registry<T> registry,String modid) {
		this.registry=registry;
		this.modid=modid;
	}
	/**
	 * <p>相比于forge这里第一个参数传入Registry而不是IForgeRegistry，可从{@link net.minecraft.core.registries.BuiltInRegistries}中获取</p>
	 * */
	public static <B> DeferredRegister<B> create(Registry<B> registry,String modid){
		return new DeferredRegister<>(registry, modid);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <I extends T> RegistryObject<I> register(final String name, final Supplier<? extends I> sup)
    {
		RegistryObject<I> obj=new RegistryObject(new ResourceLocation(modid,name),sup.get());
		objlist.add((RegistryObject<T>) obj);
		return obj;
    }
	public void register(EventBus bus) {
		for(RegistryObject<T> obj:objlist) {
			Registry.register(registry, obj.getkey(), obj.get());
		}
		objlist=null;
		modid=null;
		registry=null;
	}
}
