package majongmc.hllib.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientUtils {
	public static int frameID=0;
	public static int getcurrentframeID() {
		return frameID;
	}
	public static Player GetClientPlayer() {
		return Minecraft.getInstance().player;
	}
	public static Level GetClientLevel() {
		return Minecraft.getInstance().level;
	}
	public static Minecraft GetClient() {
		return Minecraft.getInstance();
	}
	public static void ClientStopSound() {
		Minecraft.getInstance().getSoundManager().stop();
	}
	public static int getfps() {
		return Minecraft.getInstance().getFps();
	}
	public static double fpsratio() {
		return getfps()/60.0;
	}
}
