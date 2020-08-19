package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExpenseData {
	@Id
	private String num;
	private String name;
	private String amount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
}
