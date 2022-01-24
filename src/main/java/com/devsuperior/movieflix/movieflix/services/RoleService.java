package com.devsuperior.movieflix.movieflix.services;

import com.devsuperior.movieflix.movieflix.dto.RoleDTO;
import com.devsuperior.movieflix.movieflix.entities.Role;
import com.devsuperior.movieflix.movieflix.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	@Transactional(readOnly = true)
	public List<RoleDTO> findAll(){
		List<Role> list = repository.findAll();
		return list.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
	}
}
