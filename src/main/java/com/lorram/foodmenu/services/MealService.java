package com.lorram.foodmenu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.foodmenu.dto.MealDTO;
import com.lorram.foodmenu.entities.Meal;
import com.lorram.foodmenu.repositories.MealRepository;

@Service
public class MealService {

	@Autowired
	private MealRepository repository;
	
	public Page<MealDTO> findAll(Pageable pageable) {
		Page<Meal> list = repository.findAll(pageable);
		return list.map(x -> new MealDTO(x));
	}
	
	public MealDTO findById(Long id) {
		Optional<Meal> entity = repository.findById(id);
		Meal meal = entity.orElseThrow(() -> new RuntimeException());
		return new MealDTO(meal);
	}
	
	public MealDTO insert(MealDTO dto) {
		Meal meal = new Meal();
		fromDto(dto, meal);
		meal = repository.save(meal);
		return new MealDTO(meal);
	}
	
	public MealDTO update(MealDTO dto, Long id) {
		Meal meal = repository.getReferenceById(id);
		fromDto(dto, meal);
		meal = repository.save(meal);
		return new MealDTO(meal);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} 
		catch (EmptyResultDataAccessException e) {
			throw new RuntimeException();
		}
	}
	
	private void fromDto(MealDTO mealDto, Meal meal) {
		meal.setName(mealDto.name());
		meal.setDescription(mealDto.description());
	}
}
