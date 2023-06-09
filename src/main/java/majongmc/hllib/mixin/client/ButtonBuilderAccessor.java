package majongmc.hllib.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Button.CreateNarration;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;

@Mixin(Button.Builder.class)
public interface ButtonBuilderAccessor {
	@Accessor("x")
    public int x();
	@Accessor("y")
    public int y();
	@Accessor("width")
    public int width();
	@Accessor("height")
    public int height();
	@Accessor("message")
    public Component message();
	@Accessor("onPress")
    public OnPress onPress();
	@Accessor("createNarration")
    public CreateNarration createNarration();
	@Accessor("tooltip")
    public Tooltip tooltip();
}
