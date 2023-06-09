package majongmc.hllib.client.render;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;

public class RenderShapeInGUI {
	public static final Vector3f GUINORMAL=new Vector3f(0,0,-1);
	/**
	 * <p>渲染辐射效果的一部分（扇形区域）</p>
	 * <p>相比于renderRadiation，可指定起始角和终止角</p>
	 * */
	public static void renderRadiationPart(PoseStack PoseStack,Ellipse circle,Color centercolor,Color sidecolor,int sides,float angle1,float angle2,int interval) {
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		Matrix4f matrix4f =PoseStack.last().pose();
		float[] rgba1= {centercolor.getRedfloat(),centercolor.getGreenfloat(),centercolor.getBluefloat(),centercolor.getAlphafloat()};
		float[] rgba2= {sidecolor.getRedfloat(),sidecolor.getGreenfloat(),sidecolor.getBluefloat(),sidecolor.getAlphafloat()};
		float deltasita=(angle2-angle1)/sides;
		for(int i=0;i<sides;i=i+1+interval) {
			Vector3f v1=circle.getPosition();
			Vector3f v2=circle.getPointOnCircle(deltasita*i+angle1);
			Vector3f v3=circle.getPointOnCircle(deltasita*(i+1)+angle1);
			bufferbuilder.begin(VertexFormat.Mode.TRIANGLES, DefaultVertexFormat.POSITION_COLOR);
			bufferbuilder.vertex(matrix4f, v1.x,v1.y, v1.z).color(rgba1[0],rgba1[1],rgba1[2],rgba1[3]).endVertex();
		    bufferbuilder.vertex(matrix4f, v2.x,v2.y, v2.z).color(rgba2[0],rgba2[1],rgba2[2],rgba2[3]).endVertex();
		    bufferbuilder.vertex(matrix4f, v3.x,v3.y, v3.z).color(rgba2[0],rgba2[1],rgba2[2],rgba2[3]).endVertex();
		    BufferUploader.drawWithShader(bufferbuilder.end());
		}
		RenderSystem.disableBlend();
	}
	/**
	 * <p>渲染辐射效果</p>
	 * <p>就像手游中“恭喜获得”界面中闪闪发光的背景效果一样</p>
	 * <p>输入参数：PoseStack，椭圆或圆，中心颜色，边缘颜色，边数（决定射线粗细），间隔（决定射线密度），旋转角</p>
	 * */
	public static void renderRadiation(PoseStack PoseStack,Ellipse circle,Color centercolor,Color sidecolor,int sides,int interval,float rotation) {
		renderRadiationPart(PoseStack, circle, centercolor, sidecolor, sides, rotation, 2*Mth.PI+rotation, interval);
	}
	/**
	 * <p>渲染正多边形的一部分，边数足够大时就是扇形</p>
	 * <p>输入参数：PoseStack，椭圆或圆，中心颜色，边缘颜色，边数（足够大时就是扇形，但不应过大，否则容易卡），起始角，终止角</p>
	 * */
	public static void renderRegularPolygonPart(PoseStack PoseStack,Ellipse circle,Color centercolor,Color sidecolor,int sides,float angle1,float angle2) {
		renderRadiationPart(PoseStack, circle, centercolor, sidecolor, sides, angle1, angle2,0);
	}
	/**
	 * <p>渲染正多边形的一部分，边数足够大时就是扇形</p>
	 * <p>输入参数：PoseStack，椭圆或圆，颜色，边数（足够大时就是扇形，但不应过大，否则容易卡），起始角，终止角</p>
	 * */
	public static void renderRegularPolygonPart(PoseStack PoseStack,Ellipse circle,Color color,int sides,float angle1,float angle2) {
		renderRegularPolygonPart(PoseStack, circle, color, color, sides, angle1, angle2);
	}
	/**
	 * <p>渲染正多边形，边数足够大时就是椭圆（或圆）</p>
	 * <p>输入参数：PoseStack，椭圆或圆，中心颜色，边缘颜色，边数（足够大时就是扇形，但不应过大，否则容易卡）</p>
	 * */
	public static void renderRegularPolygon(PoseStack PoseStack,Ellipse circle,Color centercolor,Color sidecolor,int sides) {
		renderRegularPolygonPart(PoseStack, circle, centercolor, sidecolor, sides, 0, 2*Mth.PI);
	}
	/**
	 * <p>渲染正多边形，边数足够大时就是椭圆（或圆）</p>
	 * <p>输入参数：PoseStack，椭圆或圆，颜色，边数（足够大时就是扇形，但不应过大，否则容易卡）</p>
	 * */
	public static void renderRegularPolygon(PoseStack PoseStack,Ellipse circle,Color color,int sides) {
		renderRegularPolygon(PoseStack, circle, color, color, sides);
	}
	/**
	 * <p>渲染环的一部分</p>
	 * <p>输入参数：PoseStack，内部椭圆（或圆），外部椭圆（或圆），颜色，边数（越大效果越好，但不应过大，否则容易卡），起始角，终止角</p>
	 * */
	public static void renderRingPart(PoseStack PoseStack,Ellipse circleout,Ellipse circlein,Color color,int sides,float angle1,float angle2) {
		float deltasita=(angle2-angle1)/sides;
		Matrix4f matrix4f =PoseStack.last().pose();
		float[] rgba= {color.getRedfloat(),color.getGreenfloat(),color.getBluefloat(),color.getAlphafloat()};
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		for(int i=0;i<sides;i++) {
			Vector3f v1=circlein.getPointOnCircle(deltasita*i+angle1);
			Vector3f v2=circleout.getPointOnCircle(deltasita*i+angle1);
			Vector3f v3=circleout.getPointOnCircle(deltasita*(i+1)+angle1);
			Vector3f v4=circlein.getPointOnCircle(deltasita*(i+1)+angle1);
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
			bufferbuilder.vertex(matrix4f, v1.x,v1.y, v1.z).color(rgba[0],rgba[1],rgba[2],rgba[3]).endVertex();
		    bufferbuilder.vertex(matrix4f, v2.x,v2.y, v2.z).color(rgba[0],rgba[1],rgba[2],rgba[3]).endVertex();
		    bufferbuilder.vertex(matrix4f, v3.x,v3.y, v3.z).color(rgba[0],rgba[1],rgba[2],rgba[3]).endVertex();
		    bufferbuilder.vertex(matrix4f, v4.x,v4.y, v4.z).color(rgba[0],rgba[1],rgba[2],rgba[3]).endVertex();
		    BufferUploader.drawWithShader(bufferbuilder.end());
		}
		RenderSystem.disableBlend();
	}
	/**
	 * <p>渲染环</p>
	 * <p>输入参数：PoseStack，内部椭圆（或圆），外部椭圆（或圆），颜色，边数（越大效果越好，但不应过大，否则容易卡）</p>
	 * */
	public static void renderRing(PoseStack PoseStack,Ellipse circleout,Ellipse circlein,Color color,int sides) {
		renderRingPart(PoseStack, circleout, circlein, color, sides, 0, 2*Mth.PI);
	}
	/**
	 * <p>渲染圆角矩形框架</p>
	 * <p>输入参数：PoseStack，x，y，长，宽，线宽，圆角半径，颜色</p>
	 * */
	public static void renderRoundedRectangleFrame(PoseStack PoseStack,int x,int y,int width,int height,int linewidth,int radius,Color color) {
		GuiComponent.fill(PoseStack, x+radius, y, x+width-radius, y+linewidth, color.getRGB());
		GuiComponent.fill(PoseStack, x+radius, y+height, x+width-radius, y+height-linewidth, color.getRGB());
		GuiComponent.fill(PoseStack, x, y+radius, x+linewidth, y+height-radius, color.getRGB());
		GuiComponent.fill(PoseStack, x+width, y+radius, x+width-linewidth, y+height-radius, color.getRGB());
		renderRingPart(PoseStack,new Circle(new Vector3f(x+radius,y+radius,0),GUINORMAL,radius),new Circle(new Vector3f(x+radius,y+radius,0),GUINORMAL,radius-linewidth),color,16,Mth.PI/2,Mth.PI);
		renderRingPart(PoseStack,new Circle(new Vector3f(x+width-radius,y+radius,0),GUINORMAL,radius),new Circle(new Vector3f(x+width-radius,y+radius,0),GUINORMAL,radius-linewidth),color,16,0,Mth.PI/2);
		renderRingPart(PoseStack,new Circle(new Vector3f(x+radius,y+height-radius,0),GUINORMAL,radius),new Circle(new Vector3f(x+radius,y+height-radius,0),GUINORMAL,radius-linewidth),color,16,Mth.PI,3*Mth.PI/2);
		renderRingPart(PoseStack,new Circle(new Vector3f(x+width-radius,y+height-radius,0),GUINORMAL,radius),new Circle(new Vector3f(x+width-radius,y+height-radius,0),GUINORMAL,radius-linewidth),color,16,3*Mth.PI/2,2*Mth.PI);
	}
	/**
	 * <p>渲染圆角矩形</p>
	 * <p>输入参数：PoseStack，x，y，长，宽，圆角半径，颜色</p>
	 * */
	public static void renderRoundedRectangle(PoseStack PoseStack,int x,int y,int width,int height,int radius,Color color) {
		GuiComponent.fill(PoseStack, x+radius, y, x+width-radius, y+radius, color.getRGB());
		GuiComponent.fill(PoseStack, x+radius, y+height-radius, x+width-radius, y+height, color.getRGB());
		GuiComponent.fill(PoseStack, x, y+radius, x+width, y+height-radius, color.getRGB());
		renderRegularPolygonPart(PoseStack,new Circle(new Vector3f(x+radius,y+radius,0),GUINORMAL,radius),color,16,Mth.PI/2,Mth.PI);
		renderRegularPolygonPart(PoseStack,new Circle(new Vector3f(x+width-radius,y+radius,0),GUINORMAL,radius),color,16,0,Mth.PI/2);
		renderRegularPolygonPart(PoseStack,new Circle(new Vector3f(x+radius,y+height-radius,0),GUINORMAL,radius),color,16,Mth.PI,3*Mth.PI/2);
		renderRegularPolygonPart(PoseStack,new Circle(new Vector3f(x+width-radius,y+height-radius,0),GUINORMAL,radius),color,16,3*Mth.PI/2,2*Mth.PI);
	}
	/**
	 * <p>精确填充</p>
	 * <p>使用方法同原版fill，但支持传入float</p>
	 * */
	public static void precisefill(PoseStack p_265170_, float p_265299_, float p_265262_, float p_265737_, float p_265091_, float p_265558_, int p_265191_) {
	      Matrix4f matrix4f = p_265170_.last().pose();
	      if (p_265299_ < p_265737_) {
	    	  float i = p_265299_;
	         p_265299_ = p_265737_;
	         p_265737_ = i;
	      }

	      if (p_265262_ < p_265091_) {
	    	  float j = p_265262_;
	         p_265262_ = p_265091_;
	         p_265091_ = j;
	      }

	      float f3 = (float)FastColor.ARGB32.alpha(p_265191_) / 255.0F;
	      float f = (float)FastColor.ARGB32.red(p_265191_) / 255.0F;
	      float f1 = (float)FastColor.ARGB32.green(p_265191_) / 255.0F;
	      float f2 = (float)FastColor.ARGB32.blue(p_265191_) / 255.0F;
	      BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
	      RenderSystem.enableBlend();
	      RenderSystem.setShader(GameRenderer::getPositionColorShader);
	      bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
	      bufferbuilder.vertex(matrix4f, p_265299_, p_265262_, p_265558_).color(f, f1, f2, f3).endVertex();
	      bufferbuilder.vertex(matrix4f, p_265299_, p_265091_, p_265558_).color(f, f1, f2, f3).endVertex();
	      bufferbuilder.vertex(matrix4f, p_265737_, p_265091_, p_265558_).color(f, f1, f2, f3).endVertex();
	      bufferbuilder.vertex(matrix4f, p_265737_, p_265262_, p_265558_).color(f, f1, f2, f3).endVertex();
	      BufferUploader.drawWithShader(bufferbuilder.end());
	      RenderSystem.disableBlend();
	}
	/**
	 * <p>精确填充</p>
	 * <p>使用方法同原版fill，但支持传入float</p>
	 * */
	public static void precisefill(PoseStack p_93173_, float p_93174_, float p_93175_, float p_93176_, float p_93177_, int p_93178_) {
		precisefill(p_93173_, p_93174_, p_93175_, p_93176_, p_93177_, 0F, p_93178_);
	}
}
