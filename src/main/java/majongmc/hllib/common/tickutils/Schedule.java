package majongmc.hllib.common.tickutils;
/**
 * <p>在指定的tick后执行finish方法，并在此之后自动移除</p>
 * <p>传入的第一个参数为延时的时间，在指定的时间后执行finish方法，后面的额外参数同Ticker，可通过getParaments调用</p>
 * */
public abstract class Schedule<T> extends Processable<T>{
	private final int delaytime;
	public Schedule(int delaytime,T... paraments) {
		super(paraments);
		this.delaytime=delaytime;
	}
	@Override
	public void tick() {
		super.tick();
		if(this.getProcess()>delaytime) {
			this.finish();
			this.remove();
		}
	}
	public abstract void finish();
}
