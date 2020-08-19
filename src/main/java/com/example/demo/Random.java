package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Random {
	@Id
	private String id;
	private String num;
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
