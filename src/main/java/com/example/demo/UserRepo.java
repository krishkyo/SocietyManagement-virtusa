package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserInfo ,String>{
	List<UserInfo> findByStatus(String status);
	List<UserInfo> findByName(String name);
	
}
