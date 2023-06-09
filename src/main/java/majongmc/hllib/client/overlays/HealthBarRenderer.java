package majongmc.hllib.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import majongmc.hllib.client.render.Color;
import majongmc.hllib.client.util.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class HealthBarRenderer {
	static long lastrecieve=0;
	static long lastattack=0;
	static float percentage=1,last=1,delay=1;
	public static boolean rendering=false;
	static Component name=Component.translatable("");
	static Component subname;
	static Color base=HealthBarAPI.BASE_DEFAULT;
	static Color buffer=HealthBarAPI.BUFFER_DEFAULT;
	static Color barc=HealthBarAPI.BAR_DEFAULT;
	static int lastframe=0;
	static int fade=0;
	@SubscribeEvent
	public static void onOverlayRender(RenderGuiOverlayEvent.Post event) {
		if(lastframe==ClientUtils.frameID)
			return;
		lastframe=ClientUtils.frameID;
		if (Minecraft.getInstance().player == null) {
	           return;
	    }
		RenderSystem.setShaderColor(1F, 1F, 1F, fade/30F);
		if(Minecraft.getInstance().level.getGameTime()-lastrecieve<20L&&Minecraft.getInstance().level.getGameTime()-lastrecieve>=0L) {
			renderbar(event.getPoseStack());
			if(fade<30)
			fade++;
		}
		else if(fade>0) {
			renderbar(event.getPoseStack());
			fade--;
		}else {
			rendering=false;
		}
		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
	}
	private static void renderbar(PoseStack stack) {
		rendering=true;
		if(last<percentage||delay<percentage) {
			last=percentage;
			delay=percentage;
		}
		if(last>percentage) {
			lastattack=Minecraft.getInstance().level.getGameTime();
			last=percentage;
		}
		if(Minecraft.getInstance().level.getGameTime()-lastattack>10&&percentage<delay) {
			delay=delay-(0.002F+0.03F*(delay-percentage))/ClientUtils.fpsratio();
		}
		HealthBarOverlay bar=new HealthBarOverlay(stack);
		bar.render(percentage, delay,name,subname,base,buffer,barc);
	}
}
