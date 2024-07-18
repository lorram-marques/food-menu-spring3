package com.lorram.foodmenu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.foodmenu.dto.ReviewDTO;
import com.lorram.foodmenu.entities.Review;
import com.lorram.foodmenu.repositories.MealRepository;
import com.lorram.foodmenu.repositories.ReviewRepository;
import com.lorram.foodmenu.repositories.UserRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<ReviewDTO> findAll(Pageable pageable) {
		Page<Review> list = repository.findAll(pageable);
		return list.map(x -> new ReviewDTO(x));
	}
	
	public ReviewDTO findById(Long id) {
		Optional<Review> entity = repository.findById(id);
		Review review = entity.orElseThrow(() -> new RuntimeException());
		return new ReviewDTO(review);
	}
	
	public ReviewDTO insert(ReviewDTO dto) {
		Review review = new Review();
		fromDto(dto, review);
		review = repository.save(review);
		return new ReviewDTO(review);
	}
	
	public ReviewDTO update(ReviewDTO dto, Long id) {
		Review review = repository.getReferenceById(id);
		fromDto(dto, review);
		review = repository.save(review);
		return new ReviewDTO(review);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} 
		catch (EmptyResultDataAccessException e) {
			throw new RuntimeException();
		}
	}
	
	private void fromDto(ReviewDTO reviewDto, Review review) {
		review.setTitle(reviewDto.title());
		review.setBody(reviewDto.body());
		review.setUser(userRepository.getReferenceById(review.getUser().getId()));
		review.setMeal(mealRepository.getReferenceById(review.getMeal().getId()));
	}
}
