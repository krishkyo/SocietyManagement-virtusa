package com.example.demo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TemoRepo extends CrudRepository<TempData, String>{

	Optional<TempData> findByName(String string);

}
