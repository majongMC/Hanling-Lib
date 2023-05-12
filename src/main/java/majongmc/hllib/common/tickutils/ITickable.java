package majongmc.hllib.common.tickutils;
/**
 * <p>基础接口，实现该接口的类还需要调用{@link TickManager}中的registerTicker进行注册，以及使用{@link TickManager}中的removeTicker手动移除</p>
 * <p>不推荐直接使用，可使用本模组提供的一些实现类</p>
 * */
public interface ITickable {
	/**
	 * <p>每tick调用一次</p>
	 * */
	void tick();
	/**
	 * <p>是否在客户端调用</p>
	 * */
	boolean isclientside();
}
