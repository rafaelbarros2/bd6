package com.devsuperior.movieflix.movieflix.services;

import com.devsuperior.movieflix.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.movieflix.dto.NewMovieDTO;
import com.devsuperior.movieflix.movieflix.entities.Genre;
import com.devsuperior.movieflix.movieflix.entities.Movie;
import com.devsuperior.movieflix.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;


	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private AuthService authService;

	@Autowired UserService userService;


//	@Transactional(readOnly = true)
//	public Page<NewMovieDTO> findAllPaged(Pageable pageable) {
//	Page<Movie> page = repository.findAll(pageable);
//	return page.map(x -> new NewMovieDTO(x));
//	}


	@Transactional(readOnly = true)
	public Page<NewMovieDTO> findByGenre(Long genreId, Pageable pageable) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> page = repository.findByGenre(genre, pageable);
		repository.findMoviesAndGenres(page.getContent());
		return page.map(x -> new NewMovieDTO(x));
	}


	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTO(entity);

	}

}
