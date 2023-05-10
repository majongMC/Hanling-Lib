package majongmc.hllib.common.network;

import java.util.function.Function;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class SimpleChannel {
	private final ResourceLocation id;
	private final Function<FriendlyByteBuf, INDP> decoder;
	
	public SimpleChannel(ResourceLocation id,Function<FriendlyByteBuf, INDP> decoder) {
		this.id=id;
		this.decoder=decoder;
	}
	/**
	 * <p>注意：与forge相比，第一个参数直接传入ServerPlayer</p>
	 * */
	public void send(ServerPlayer player,INDP datapack) {
		FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
		datapack.toBytes(buf);
		ServerPlayNetworking.send(player, id, buf);
	}
	public void sendToServer(INDP datapack) {
		FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
		datapack.toBytes(buf);
		ClientPlayNetworking.send(id, buf);
	}
	/**
	 * <p>需要在客户端/服务端初始化时注册，哪边要接受数据包就注册那边</p>
	 * */
	public void registerClientHandler() {
		ClientPlayNetworking.registerGlobalReceiver(id, (client, handler, buf, responseSender) -> {
			NetworkEvent.Context ctx=new NetworkEvent.Context(null,handler, responseSender);
			INDP datapack =decoder.apply(buf);
			datapack.handler(()->ctx);
		});
	}
	/**
	 * <p>需要在客户端/服务端初始化时注册，哪边要接受数据包就注册那边</p>
	 * */
	public void registerServerHandler() {
		ServerPlayNetworking.registerGlobalReceiver(id, (server, player, handler, buf, responseSender)->{
			NetworkEvent.Context ctx=new NetworkEvent.Context(server,handler, responseSender);
			INDP datapack =decoder.apply(buf);
			datapack.handler(()->ctx);
		});
	}
}
