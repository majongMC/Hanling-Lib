package majongmc.hllib.client.overlays;

import majongmc.hllib.client.render.Color;
import majongmc.hllib.client.util.ClientUtils;
import net.minecraft.network.chat.Component;

public class HealthBarAPI {
	public static final Color BASE_DEFAULT=new Color(-2139062144);
	public static final Color BUFFER_DEFAULT=new Color(-1);
	public static final Color BAR_DEFAULT=new Color(-1237980);
	public static void DisplayHealthBar(float percentage,Component name,Component subname) {
		HealthBarRenderer.lastrecieve=ClientUtils.GetClientLevel().getGameTime();
		HealthBarRenderer.percentage=percentage;
		HealthBarRenderer.name=name;
		HealthBarRenderer.subname=subname;
		HealthBarRenderer.base=BASE_DEFAULT;
		HealthBarRenderer.buffer=BUFFER_DEFAULT;
		HealthBarRenderer.barc=BAR_DEFAULT;
	}
	public static void DisplayHealthBar(float percentage,Component name,Component subname,Color base,Color buffer,Color bar) {
		HealthBarRenderer.lastrecieve=ClientUtils.GetClientLevel().getGameTime();
		HealthBarRenderer.percentage=percentage;
		HealthBarRenderer.name=name;
		HealthBarRenderer.subname=subname;
		HealthBarRenderer.base=base;
		HealthBarRenderer.buffer=buffer;
		HealthBarRenderer.barc=bar;
	}
}
