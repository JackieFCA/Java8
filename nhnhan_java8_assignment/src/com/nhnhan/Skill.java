package com.nhnhan;

public class Skill {
	private String skill;
	private int exp;

	public Skill(String skill, int exp) {
		this.skill = skill;
		this.exp = exp;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
}
