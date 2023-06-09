package majongmc.hllib.client.render;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
/**
 * <p>用于在世界中渲染指定图形</p>
 * */
@OnlyIn(Dist.CLIENT)
public class RenderShapeInLevel {
	/**
	 * <p>渲染棱台</p>
	 * <p>输入：Matrix4f m4f,VertexConsumer vc（渲染的基本参数，注意顶点缓冲区采用的渲染类型中顶点的形式为四边形，且不要有贴图，否则可能导致错误，这里建议用原版闪电的渲染类型）</p>
	 * <p>后面几个参数分别为边数（数值足够大时就是圆台，但不应该过大，否则容易卡），下、上底的中心坐标，上底的法向量（上、下底可不一定平行），下、上底的半径，下、上底的颜色</p>
	 * */
	public static void renderFrustum(Matrix4f m4f,VertexConsumer vc,int n,Vector3f p1,Vector3f p2,Vector3f normal2,float r1,float r2,Color color1,Color color2) {
		Vector3f normal=new Vector3f(p2).sub(p1);
		Circle c1=new Circle(p1,normal,r1);
		Circle c2=new Circle(p2,normal2,r2);
		float[] rgba1= {color1.getRedfloat(),color1.getGreenfloat(),color1.getBluefloat(),color1.getAlphafloat()};
		float[] rgba2= {color2.getRedfloat(),color2.getGreenfloat(),color2.getBluefloat(),color2.getAlphafloat()};
		float deltasita=2*Mth.PI/n;
		for(int i=0;i<n;i++) {
			Vector3f v1=c1.getPointOnCircle(deltasita*(i+1));
			Vector3f v2=c1.getPointOnCircle(deltasita*i);
			Vector3f v3=c2.getPointOnCircle(deltasita*i);
			Vector3f v4=c2.getPointOnCircle(deltasita*(i+1));
			vc.vertex(m4f,v4.x,v4.y, v4.z).color(rgba2[0],rgba2[1],rgba2[2],rgba2[3]).endVertex();
			vc.vertex(m4f,v3.x,v3.y, v3.z).color(rgba2[0],rgba2[1],rgba2[2],rgba2[3]).endVertex();
			vc.vertex(m4f,v2.x,v2.y, v2.z).color(rgba1[0],rgba1[1],rgba1[2],rgba1[3]).endVertex();
			vc.vertex(m4f,v1.x,v1.y, v1.z).color(rgba1[0],rgba1[1],rgba1[2],rgba1[3]).endVertex();
		}
	}
	/**
	 * <p>此方法可不输入Vector3f normal2，在这种情况下上下底平行</p>
	 * */
	public static void renderFrustum(Matrix4f m4f,VertexConsumer vc,int n,Vector3f p1,Vector3f p2,float r1,float r2,Color color1,Color color2) {
		RenderShapeInLevel.renderFrustum(m4f, vc, n, p1, p2, new Vector3f(p2).sub(p1), r1, r2, color1, color2);
	}
}
