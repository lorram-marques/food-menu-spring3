package com.lorram.foodmenu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.foodmenu.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

}
