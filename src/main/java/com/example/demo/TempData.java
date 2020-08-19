package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TempData {
	@Id
	private String name;
	private String balance;
	private String expense;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getExpense() {
		return expense;
	}
	public void setExpense(String expense) {
		this.expense = expense;
	}
}
