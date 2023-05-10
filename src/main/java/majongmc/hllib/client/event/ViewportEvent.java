package majongmc.hllib.client.event;

import org.jetbrains.annotations.ApiStatus;

import majongmc.hllib.common.event.Event;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;

public class ViewportEvent extends Event{
	private final GameRenderer renderer;
    private final Camera camera;
    private final double partialTick;

    @ApiStatus.Internal
    public ViewportEvent(GameRenderer renderer, Camera camera, double partialTick)
    {
        this.renderer = renderer;
        this.camera = camera;
        this.partialTick = partialTick;
    }

    /**
     * {@return the game renderer}
     */
    public GameRenderer getRenderer()
    {
        return renderer;
    }

    /**
     * {@return the camera information}
     */
    public Camera getCamera()
    {
        return camera;
    }

    /**
     * {@return the partial tick}
     */
    public double getPartialTick()
    {
        return partialTick;
    }
    public static class ComputeCameraAngles extends ViewportEvent
    {
        private float yaw;
        private float pitch;
        private float roll;

        @ApiStatus.Internal
        public ComputeCameraAngles(GameRenderer renderer, Camera camera, double renderPartialTicks, float yaw, float pitch, float roll)
        {
            super(renderer, camera, renderPartialTicks);
            this.setYaw(yaw);
            this.setPitch(pitch);
            this.setRoll(roll);
        }

        /**
         * {@return the yaw of the player's camera}
         */
        public float getYaw()
        {
            return yaw;
        }

        /**
         * Sets the yaw of the player's camera.
         *
         * @param yaw the new yaw
         */
        public void setYaw(float yaw)
        {
            this.yaw = yaw;
        }

        /**
         * {@return the pitch of the player's camera}
         */
        public float getPitch()
        {
            return pitch;
        }

        /**
         * Sets the pitch of the player's camera.
         *
         * @param pitch the new pitch
         */
        public void setPitch(float pitch)
        {
            this.pitch = pitch;
        }

        /**
         * {@return the roll of the player's camera}
         */
        public float getRoll()
        {
            return roll;
        }

        /**
         * Sets the roll of the player's camera.
         *
         * @param roll the new roll
         */
        public void setRoll(float roll)
        {
            this.roll = roll;
        }
    }
}
