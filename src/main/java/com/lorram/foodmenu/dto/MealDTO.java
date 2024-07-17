package com.lorram.foodmenu.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.lorram.foodmenu.entities.Meal;

public record MealDTO(Long id, String name, String description, List<ReviewDTO> reviews) {

	public MealDTO(Meal meal) {
		this(meal.getId(), 
			 meal.getName(), 
			 meal.getDescription(),
			 meal.getReviews().stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList()));
	}
}
