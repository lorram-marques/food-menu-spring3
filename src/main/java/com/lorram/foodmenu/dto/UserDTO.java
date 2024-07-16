package com.lorram.foodmenu.dto;

import java.util.List;

public record UserDTO(Long id, String name, String email, List<ReviewDTO> reviews) {

}
