package com.devsuperior.movieflix.movieflix.services;

import com.devsuperior.movieflix.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.movieflix.entities.User;
import com.devsuperior.movieflix.movieflix.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
		
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly = true)
	public UserDTO getProfile() {
        return new UserDTO(authService.authenticated());
    }
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		if(user == null) {
			logger.error("User Not Found: " + username);
			throw new UsernameNotFoundException("Email Not Found");
		}
		logger.info("User Found: " + username);
		return user;
	}
}
