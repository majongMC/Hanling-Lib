package majongmc.hllib.common.skill;

import net.minecraft.world.entity.ai.goal.Goal;
/**
 * <p>技能系统详解</p>
 * <p>本AI技能系统的运作类似于抽卡，同一时间只会有一个技能运行，原版的优先级不会影响技能的分配，每一个技能结束后会抽取下一个技能</p>
 * <p>要使用本系统，你需要让实体实现{@link ISkillable}接口，AI(Goal)继承Skill抽象类</p>
 * <p>{@link ISkillable}接口中，getskill和setskill为获取和设置当前的技能，每一个技能都有一个Byte类型的技能ID</p>
 * <p>nextskill为抽取下一个技能，传入的参数为上一个技能的ID，返回值为下一个技能的ID，注意不要重复抽与上一个技能相同的技能，否则可能导致技能不能正常初始化，解决这个问题的方案为抽取一个时间极短的空闲技能，然后空闲技能结束后再重复抽取这个技能</p>
 * <p>在Skill类中，getID()为获取当前技能ID，additionalCanUse同原版的canUse，但请不要在继承了Skill类的Goal中覆写原版的canUse方法，否则会造成技能意外执行，请覆写additionalCanUse</p>
 * <p>技能结束时请调用finish()方法，系统会自动抽取并执行下一个技能</p>
 * */
public abstract class Skill extends Goal{
	private ISkillable skillable;
	public Skill(ISkillable skillable) {
		this.skillable=skillable;
	}
	@Override
	public boolean canUse() {
		if(skillable.getskill()!=getID())
			return false;
		else
			return additionalCanUse();
	}
	public abstract byte getID();
	public abstract boolean additionalCanUse();
	public void finish() {
		skillable.setskill(skillable.nextskill(getID()));
	}
}
