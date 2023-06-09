package majongmc.hllib.client.render;

import org.joml.Vector3f;

import net.minecraft.util.Mth;
/**
 * <p>椭圆参数方程的封装类</p>
 * */
public class Ellipse {
	private final Vector3f u,v,pos;
	private final float longaxis,shortaxis;
	/**
	 * <p>输入参数：圆心，法线向量，长轴，短轴</p>
	 * */
	public Ellipse(Vector3f pos,Vector3f normal,float longaxis,float shortaxis) {
		Vector3f n =new Vector3f(normal);
		this.pos=new Vector3f(pos);
		this.longaxis=longaxis;
		this.shortaxis=shortaxis;
		 if(n.x==0&&n.y==0)
		    	u=new Vector3f(1,0,0);
		    else
		    	u=new Vector3f(n.y,-n.x,0).normalize();
		 v=n.cross(u).normalize();
	}
	/**输出椭圆上的一个点：
	 * <p>输入角度（弧度），范围0~2PI</p>
	 * */
	public Vector3f getPointOnCircle(float sita) {
		return new Vector3f(pos.x+longaxis*u.x*Mth.cos(sita)+shortaxis*v.x*Mth.sin(sita),pos.y+longaxis*u.y*Mth.cos(sita)+shortaxis*v.y*Mth.sin(sita),pos.z+longaxis*u.z*Mth.cos(sita)+shortaxis*v.z*Mth.sin(sita));
	}
	/**
	 * <p>输出圆心坐标</p>
	 * */
	public Vector3f getPosition() {
		return pos;
	}
	/**
	 * <p>输出长轴</p>
	 * */
	public float getLongaxis() {
		return longaxis;
	}
	/**
	 * <p>输出短轴/p>
	 * */
	public float getShortaxis() {
		return shortaxis;
	}
}
