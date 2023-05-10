package majongmc.hllib.common.registry;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;

public final class RegistryObject<T> implements Supplier<T>{
	private final ResourceLocation name;
	private final T value;
	RegistryObject(ResourceLocation name,T value){
	    this.name=name;
	    this.value=value;
	}
	@Override
	public T get() {
        return value;
	}
	public ResourceLocation getkey() {
        return name;
	}
}
