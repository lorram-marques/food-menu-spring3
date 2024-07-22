package com.lorram.foodmenu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.foodmenu.dto.UserDTO;
import com.lorram.foodmenu.entities.User;
import com.lorram.foodmenu.repositories.UserRepository;
import com.lorram.foodmenu.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDTO(x));
	}
	
	public UserDTO findById(Long id) {
		Optional<User> entity = repository.findById(id);
		User user = entity.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(user);
	}
	
	public UserDTO insert(UserDTO dto) {
		User user = new User();
		fromDto(dto, user);
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	public UserDTO update(UserDTO dto, Long id) {
		User user = repository.getReferenceById(id);
		fromDto(dto, user);
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
			} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void fromDto(UserDTO userDto, User user) {
		user.setName(userDto.name());
		user.setEmail(userDto.email());
	}
}
