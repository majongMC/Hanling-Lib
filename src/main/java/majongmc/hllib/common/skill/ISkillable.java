package majongmc.hllib.common.skill;
/**
 * <p>实体实现这个接口</p>
 * */
public interface ISkillable {
	byte getskill();
	byte nextskill(byte currentskill);
	void setskill(byte skill);
}
