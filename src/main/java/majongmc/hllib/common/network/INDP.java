package majongmc.hllib.common.network;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;

public interface INDP {
	void toBytes(FriendlyByteBuf buf);
	void handler(Supplier<NetworkEvent.Context> ctx);
}
