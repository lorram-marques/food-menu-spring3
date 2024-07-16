package com.lorram.foodmenu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.foodmenu.entities.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>{

}
