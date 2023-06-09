package majongmc.hllib.client.render;

import org.joml.Vector3f;
/**
 * <p>圆参数方程的封装类，继承于椭圆</p>
 * */
public class Circle extends Ellipse{
	/**
	 * <p>输入参数：圆心，法线向量，半径</p>
	 * */
	public Circle(Vector3f pos, Vector3f normal, float r) {
		super(pos, normal, r, r);
	}
	/**
	 * <p>输出半径</p>
	 * */
	public float getRadius() {
		return getLongaxis();
	}
}
