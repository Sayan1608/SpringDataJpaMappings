package com.mapping.repositories;

import com.mapping.entities.MovieSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieSeatRepository extends JpaRepository<MovieSeat,Long> {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<MovieSeat> findById(Long aLong);
}
