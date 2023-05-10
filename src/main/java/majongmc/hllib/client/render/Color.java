package majongmc.hllib.client.render;

public class Color {
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color LIGHT_GRAY = new Color(192, 192, 192);
    public static final Color GRAY = new Color(128, 128, 128);
    public static final Color DARK_GRAY = new Color(64, 64, 64);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color PINK = new Color(255, 175, 175);
    public static final Color ORANGE = new Color(255, 200, 0);
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color MAGENTA = new Color(255, 0, 255);
    public static final Color CYAN = new Color(0, 255, 255);
    public static final Color BLUE = new Color(0, 0, 255);
	private int value;
	
	public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }
	public Color(int r, int g, int b, int a) {
        value = ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((b & 0xFF) << 0);
    }
	public int getRGB() {
        return value;
    }
	public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }
    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }
    public int getBlue() {
        return (getRGB() >> 0) & 0xFF;
    }
    public int getAlpha() {
        return (getRGB() >> 24) & 0xff;
    }
    
}