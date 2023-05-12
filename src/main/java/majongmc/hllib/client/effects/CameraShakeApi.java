package majongmc.hllib.client.effects;

import majongmc.hllib.client.util.ClientUtils;

public class CameraShakeApi {
	public static int shakeframe=0;
	public static float extent=1.0F;
	/**
	 * <p>相机抖动特效，仅支持在客户端触发</p>
	 * <p>输入参数为抖动持续的帧数（以60fps为基准，fps不为60时模组会自动校准）</p>
	 * */
	public static void CameraShakeClient(int shakeframe) {
		CameraShakeApi.shakeframe=(int) (shakeframe*ClientUtils.fpsratio());
	}
	/**
	 * <p>设置相机抖动的幅度</p>
	 * */
	public static void setExtent(float extent) {
		CameraShakeApi.extent=extent;
	}
}
