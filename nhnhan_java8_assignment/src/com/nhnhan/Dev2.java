package com.nhnhan;

import java.util.ArrayList;
import java.util.List;

public class Dev2 {
	int id;
	String name;
	List<Skill> skillList = new ArrayList<>();

	public Dev2(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
	}
}
