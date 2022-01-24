package com.devsuperior.movieflix.movieflix.resources;

import com.devsuperior.movieflix.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.movieflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> getProfile() {
        UserDTO dto = service.getProfile();
        return ResponseEntity.ok(dto);
    }
}
