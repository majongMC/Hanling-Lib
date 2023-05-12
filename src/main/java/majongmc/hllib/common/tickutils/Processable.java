package majongmc.hllib.common.tickutils;
/**
 * <p>相比于Ticker，本类会有一个进度值，每tick+1，可通过getProcess获取，setProcess设定</p>
 * */
public abstract class Processable<T> extends Ticker<T>{
	private int process=0;
	public Processable(T... paraments) {
		super(paraments);
	}
	@Override
	public void tick() {
		process++;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process=process;
	}
}
