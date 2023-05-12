package majongmc.hllib.common.skill;
/**
 * <p>空闲技能，此技能会让生物休息一段时间，不会在这期间使用其他技能，输入的参数为：技能所有者，技能ID，空闲的tick叔</p>
 * */
public class IdleSkill extends Skill{
	private int process=0;
	private final int idletime;
	private final byte id;
	public IdleSkill(ISkillable skillable,byte id,int idleticks) {
		super(skillable);
		this.id=id;
		this.idletime=idleticks/2;
	}

	@Override
	public byte getID() {
		return id;
	}

	@Override
	public boolean additionalCanUse() {
		return true;
	}
	@Override
	public void start() {
		this.process=0;
	}
	@Override
	public void tick() {
		if(process>=idletime)
			this.finish();
		this.process++;
	}
}
