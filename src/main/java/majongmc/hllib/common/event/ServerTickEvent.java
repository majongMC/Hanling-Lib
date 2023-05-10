package majongmc.hllib.common.event;

import net.minecraft.server.MinecraftServer;

public class ServerTickEvent extends Event{
	private final MinecraftServer server;
	public ServerTickEvent(MinecraftServer server) {
		this.server=server;
	}
	public MinecraftServer getServer()
    {
        return server;
    }
}
