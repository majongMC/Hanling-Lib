package majongmc.hllib.client.event;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;

import majongmc.hllib.common.event.Event;
/**
 * <p>注意：该事件相比于Forge缺少NamedGuiOverlay overlay 参数和getOverlay()方法</p>
 * <p>该事件只能用于渲染自己的HUD，无法控制原版HUD的渲染，每帧触发一次</p>
 * */
public class RenderGuiOverlayEvent extends Event{
	private final Window window;
    private final PoseStack poseStack;
    private final float partialTick;

    protected RenderGuiOverlayEvent(Window window, PoseStack poseStack, float partialTick)
    {
        this.window = window;
        this.poseStack = poseStack;
        this.partialTick = partialTick;
    }

    public Window getWindow()
    {
        return window;
    }

    public PoseStack getPoseStack()
    {
        return poseStack;
    }

    public float getPartialTick()
    {
        return partialTick;
    }
    public static class Post extends RenderGuiOverlayEvent
    {
        public Post(Window window, PoseStack poseStack, float partialTick)
        {
            super(window, poseStack, partialTick);
        }
    }
}
