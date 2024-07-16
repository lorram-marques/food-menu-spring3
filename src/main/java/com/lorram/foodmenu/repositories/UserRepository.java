package com.lorram.foodmenu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.foodmenu.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
