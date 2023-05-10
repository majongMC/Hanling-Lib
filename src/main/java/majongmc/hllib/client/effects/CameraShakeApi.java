package majongmc.hllib.client.effects;

import majongmc.hllib.client.util.ClientUtils;

public class CameraShakeApi {
	public static int shakeframe=0;
	public static float extent=1.0F;
	public static void CameraShakeClient(int shakeframe) {
		CameraShakeApi.shakeframe=(int) (shakeframe*ClientUtils.fpsratio());
	}
	public static void setExtent(float extent) {
		CameraShakeApi.extent=extent;
	}
}
