package majongmc.hllib.common.tickutils;
/**
 * <p>最基本的实现类，泛型控制传入和获取的参数的类型</p>
 * <p>继承并实例化后会自动注册，但需要手动调用remove方法移除</p>
 * <p>可传入任意个参数（包括0个），通过getParaments获取传入的参数（数组），与传入的顺序相同</p>
 * */
public abstract class Ticker<T> implements ITickable{
	private final T[] paraments;
	public Ticker(T... paraments) {
		this.paraments=paraments;
		TickManager.registerTicker(this);
		this.init();
	}
	/**
	 * <p>初始化方法，继承并实例化时会被调用</p>
	 * */
	public void init() {}
	/**
	 * <p>获取传入的参数（数组），与传入的顺序相同</p>
	 * */
	public T[] getParaments() {
		return paraments;
	}
	/**
	 * <p>移除Ticker</p>
	 * */
	public void remove() {
		TickManager.removeTicker(this);
	}
}
