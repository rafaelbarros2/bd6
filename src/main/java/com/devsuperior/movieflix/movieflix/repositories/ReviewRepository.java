package com.devsuperior.movieflix.movieflix.repositories;

import com.devsuperior.movieflix.movieflix.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
