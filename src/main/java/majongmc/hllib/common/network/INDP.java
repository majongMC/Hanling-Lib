package majongmc.hllib.common.network;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
/**
 * <p>让你的包体定义类实现这个接口</p>
 * */
public interface INDP {
	void toBytes(FriendlyByteBuf buf);
	void handler(Supplier<NetworkEvent.Context> ctx);
}
