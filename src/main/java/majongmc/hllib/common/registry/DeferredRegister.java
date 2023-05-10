package majongmc.hllib.common.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import majongmc.hllib.common.event.EventBus;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

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
