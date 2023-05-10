package majongmc.hllib.client.render;

import org.joml.Vector3f;

import net.minecraft.util.Mth;

public class Circle {
	private final Vector3f u,v,pos;
	private final float r;
	/**
	 * <p>输入参数：圆心，法线向量，半径</p>
	 * */
	public Circle(Vector3f pos,Vector3f normal,float r) {
		Vector3f n =new Vector3f(normal);
		this.pos=new Vector3f(pos);
		this.r=r;
		 if(n.x==0&&n.y==0)
		    	u=new Vector3f(1,0,0);
		    else
		    	u=new Vector3f(n.y,-n.x,0).normalize();
		 v=n.cross(u).normalize();
	}
	/**输出圆上的一个点：
	 * <p>输入角度（弧度），范围0~2PI</p>
	 * */
	public Vector3f getPointOnCircle(float sita) {
		return new Vector3f(pos.x+r*(u.x*Mth.cos(sita)+v.x*Mth.sin(sita)),pos.y+r*(u.y*Mth.cos(sita)+v.y*Mth.sin(sita)),pos.z+r*(u.z*Mth.cos(sita)+v.z*Mth.sin(sita)));
	}
}
