package com.lorram.foodmenu.dto;

import java.util.List;

public record MealDTO(Long id, String name, String description, List<ReviewDTO> reviews) {
	
}
