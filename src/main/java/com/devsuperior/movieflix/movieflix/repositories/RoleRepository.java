package com.devsuperior.movieflix.movieflix.repositories;

import com.devsuperior.movieflix.movieflix.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
