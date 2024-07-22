package com.lorram.foodmenu.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.foodmenu.dto.MealDTO;
import com.lorram.foodmenu.dto.ReviewDTO;
import com.lorram.foodmenu.entities.Meal;
import com.lorram.foodmenu.repositories.MealRepository;
import com.lorram.foodmenu.services.exceptions.ResourceNotFoundException;

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
		Meal meal = entity.orElseThrow(() -> new ResourceNotFoundException(id));
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
	
	public List<ReviewDTO> findReviews(Long id) {
		Optional<Meal> entity = repository.findById(id);
		Meal meal = entity.orElseThrow(() -> new ResourceNotFoundException(id));
		List<ReviewDTO> list = meal.getReviews().stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
		return list;
	} 
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void fromDto(MealDTO mealDto, Meal meal) {
		meal.setName(mealDto.name());
		meal.setDescription(mealDto.description());
	}
}
