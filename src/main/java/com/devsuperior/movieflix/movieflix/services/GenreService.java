package com.devsuperior.movieflix.movieflix.services;

import com.devsuperior.movieflix.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.movieflix.entities.Genre;
import com.devsuperior.movieflix.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GenreService {

	@Autowired
	private GenreRepository repository;

	@Autowired AuthService authService;

	@Transactional(readOnly = true)
	public Page<GenreDTO> findAllPaged(Pageable pageable) {
		Page<Genre> list = repository.findAll(pageable);
		return list.map(x -> new GenreDTO(x));
	}

	public List<GenreDTO> findAll() {
		authService.authenticated();
		List<Genre> list = repository.findAll();
		return list.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());

	}
}
