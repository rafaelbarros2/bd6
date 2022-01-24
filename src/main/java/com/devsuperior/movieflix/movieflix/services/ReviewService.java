package com.devsuperior.movieflix.movieflix.services;

import com.devsuperior.movieflix.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.movieflix.entities.Movie;
import com.devsuperior.movieflix.movieflix.entities.Review;
import com.devsuperior.movieflix.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private AuthService authService;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired UserService userService;

	@Transactional(readOnly = true)
	public Page<ReviewDTO> findAllPaged(Pageable pageable) {
		Page<Review> list = repository.findAll(pageable);
		return list.map(x -> new ReviewDTO(x));
	}

	@Transactional(readOnly = true)
	public ReviewDTO findById(Long id) {
		authService.validateSelfOrAdmin(userService.getProfile().getId());
		Optional<Review> obj = repository.findById(id);
		Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ReviewDTO(entity);
	}

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		entity.setText(dto.getText());
		entity.setMovie(movieRepository.getOne(dto.getMovieId()));
		entity.setUser(authService.authenticated());
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}

}
