package majongmc.hllib.common.skill;

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
