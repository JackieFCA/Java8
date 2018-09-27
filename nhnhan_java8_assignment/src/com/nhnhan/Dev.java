package com.nhnhan;

public class Dev {
	int id;
	String name;
	String skill;
	int exp;

	public Dev(int id, String name, String skill, int exp) {
		this.id = id;
		this.name = name;
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

	public Dev(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
