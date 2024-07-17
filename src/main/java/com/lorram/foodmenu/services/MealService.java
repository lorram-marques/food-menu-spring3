package com.lorram.foodmenu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.foodmenu.dto.MealDTO;
import com.lorram.foodmenu.entities.Meal;
import com.lorram.foodmenu.repositories.MealRepository;

@Service
public class MealService {

	@Autowired
	public MealRepository repository;
	
	public Page<MealDTO> findAll(Pageable pageable) {
		Page<Meal> list = repository.findAll(pageable);
		return list.map(x -> new MealDTO(x));
	}
	
}
