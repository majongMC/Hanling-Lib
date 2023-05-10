package majongmc.hllib.common.network;

import org.jetbrains.annotations.Nullable;

import majongmc.hllib.client.util.ClientUtils;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class NetworkEvent {
	public static class Context
    {
		private final MinecraftServer server;
		private final PacketListener netHandler;
		//private final PacketSender responseSender;
		private boolean packetHandled=false;
		public Context(@Nullable MinecraftServer server,PacketListener handler, PacketSender responseSender) {
			this.server=server;
			this.netHandler=handler;
			//this.responseSender=responseSender;
		}
		public void enqueueWork(Runnable runnable) {
			if (netHandler instanceof ServerGamePacketListenerImpl) {
				server.execute(runnable);
			}else
				ClientUtils.GetClient().execute(runnable);
		}
		public void setPacketHandled(boolean packetHandled) {
            this.packetHandled = packetHandled;
        }
		public boolean getPacketHandled()
        {
            return packetHandled;
        }
		@Nullable
        public ServerPlayer getSender()
        {
            if (netHandler instanceof ServerGamePacketListenerImpl)
            {
                ServerGamePacketListenerImpl netHandlerPlayServer = (ServerGamePacketListenerImpl) netHandler;
                return netHandlerPlayServer.player;
            }
            return null;
        }
    }
}
