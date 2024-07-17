package com.lorram.foodmenu.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.lorram.foodmenu.entities.User;

public record UserDTO(Long id, String name, String email, List<ReviewDTO> reviews) {

	public UserDTO(User user) {
		this(user.getId(), 
			 user.getName(), 
			 user.getEmail(), 
			 user.getReviews().stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList()));
	}
}
