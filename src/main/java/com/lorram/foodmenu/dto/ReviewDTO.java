package com.lorram.foodmenu.dto;

import com.lorram.foodmenu.entities.Review;

public record ReviewDTO(Long id, String title, String body, Long reviewId, Long mealId) {

	public ReviewDTO(Review review) {
        this(review.getId(), 
        	 review.getTitle(), 
        	 review.getBody(), 
        	 review.getId(), 
        	 review.getMeal().getId());
    }
}
