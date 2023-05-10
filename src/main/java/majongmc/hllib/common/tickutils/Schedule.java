package majongmc.hllib.common.tickutils;

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
