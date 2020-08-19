package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepo extends CrudRepository<ExpenseData, String>{
	
}
