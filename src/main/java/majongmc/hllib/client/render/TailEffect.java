package majongmc.hllib.client.render;

import java.util.LinkedList;
import java.util.Queue;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.VertexConsumer;
/**
 * <p>渲染拖尾效果<p>
 * <p>首先在实体中建立一个TailEffect成员变量并将其实例化（只要保证一个实体对应一个TailEffect实例就行）<p>
 * <p>然后在实体渲染器的render方法中调用TailEffect中的render方法<p>
 * */
public class TailEffect {
	private final int sides,maxlength;
	private final Color color1,color2;
	private final Queue<Vector3f> points = new LinkedList<>();
	private final float r;
	/**
	 * <p>参数：边数（效果越大越好，但更吃性能），最大长度，最大半径，径头部颜色，尾部颜色<p>
	 * */
	public TailEffect(int sides,int maxlength,float r,Color color1,Color color2) {
		this.sides=sides;
		this.maxlength=maxlength;
		this.color1=color1;
		this.color2=color2;
		this.r=r;
	}
	/**
	 * <p>前面两个参数不用多说了吧，注意顶点缓冲区采用的渲染类型中顶点的形式为四边形，且不要有贴图，否则可能导致错误，这里建议用原版闪电的渲染类型<p>
	 * * <p>最后面那个参数为实体的坐标（建议利用partialTicks进行插值来保证动画的顺滑）<p>
	 * */
	public void render(Matrix4f m4f,VertexConsumer vc,Vector3f pos) {
		if(!points.isEmpty()&&points.peek().equals(pos))
			return;
		points.add(pos);
		if(points.size()>maxlength)
			points.poll();
		if(points.size()<2)
			return;
		Vector3f[] p=(Vector3f[]) points.toArray(new Vector3f[points.size()]);
		for(int j=0;j<p.length;j++)
			p[j]=new Vector3f(p[j]).sub(pos);
		for(int i=p.length-1;i>0;i--) {
			if(i==1)
				RenderShapeInLevel.renderFrustum(m4f, vc, sides, p[i], p[i-1], ((float)i)/(p.length-1)*r, ((float)(i-1))/(p.length-1)*r, blendcolor(color1,color2,((float)i)/(p.length-1)), blendcolor(color1,color2,((float)(i-1))/(p.length-1)));
			else
				RenderShapeInLevel.renderFrustum(m4f, vc, sides, p[i], p[i-1],new Vector3f(p[i-2]).sub(p[i-1]), ((float)i)/(p.length-1)*r, ((float)(i-1))/(p.length-1)*r, blendcolor(color1,color2,((float)i)/(p.length-1)), blendcolor(color1,color2,((float)(i-1))/(p.length-1)));
		}
	}
	private Color blendcolor(Color color1,Color color2,float c1percent) {
		return new Color((int)(color1.getRed()*c1percent+color2.getRed()*(1-c1percent)),(int)(color1.getGreen()*c1percent+color2.getGreen()*(1-c1percent)),(int)(color1.getBlue()*c1percent+color2.getBlue()*(1-c1percent)),(int)(color1.getAlpha()*c1percent+color2.getAlpha()*(1-c1percent)));
	}
}
