package com.lorram.foodmenu.dto;

import com.lorram.foodmenu.entities.Review;

public record ReviewDTO(Long id, String title, String body, Long userId, Long mealId) {

	public ReviewDTO(Review review) {
        this(review.getId(), 
        	 review.getTitle(), 
        	 review.getBody(), 
        	 review.getUser().getId(),
        	 review.getMeal().getId());
    }
}
