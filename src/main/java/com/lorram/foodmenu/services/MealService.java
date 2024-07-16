package com.lorram.foodmenu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorram.foodmenu.repositories.MealRepository;

@Service
public class MealService {

	@Autowired
	public MealRepository repository;
	
	//TODO crud
	
}
