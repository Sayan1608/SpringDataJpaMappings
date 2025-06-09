package com.mapping.repositories;

import com.mapping.entities.MovieSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSeatRepository extends JpaRepository<MovieSeat,Long> {
}
