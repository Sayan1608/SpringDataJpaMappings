package com.mapping.services;

import com.mapping.entities.MovieSeat;
import com.mapping.entities.UserEntity;
import com.mapping.repositories.MovieSeatRepository;
import com.mapping.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieSeatBookingService {

    private final MovieSeatRepository movieSeatRepository;
    private final UserRepository userRepository;

    public MovieSeatBookingService(MovieSeatRepository movieSeatRepository, UserRepository userRepository) {
        this.movieSeatRepository = movieSeatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserEntity bookSeatUsingOptimisticLock(Long seatId, Long userId){
        System.out.println("User id " + userId + " trying to book seat with id : " + seatId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MovieSeat movieSeat = movieSeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id : " + seatId));
        if(movieSeat.isBooked()){
            throw new RuntimeException("Seat with id "+ seatId + " already booked");
        }
        movieSeat.setBooked(true);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User with id : "+userId + " not found"));
        user.getMovieSeats().add(movieSeat);
        movieSeat.getAudiences().add(user);
        UserEntity savedUser = userRepository.save(user);
        System.out.println("User id " + userId + " successfully booked seat with id : " + seatId);
        return savedUser;
    }

    @Transactional
    public UserEntity bookSeatUsingPessimisticLock(Long seatId, Long userId){
        System.out.println("User id " + userId + " trying to book seat pessimistically with id : " + seatId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MovieSeat movieSeat = movieSeatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id : " + seatId));
        if(movieSeat.isBooked()){
            throw new RuntimeException("Seat with id "+ seatId + " already booked");
        }
        movieSeat.setBooked(true);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User with id : "+userId + " not found"));
        user.getMovieSeats().add(movieSeat);
        movieSeat.getAudiences().add(user);
        UserEntity savedUser = userRepository.save(user);
        System.out.println("User id " + userId + " successfully booked seat pessimistically with id : " + seatId);
        return savedUser;
    }

    public MovieSeat createNewMovieSeat(MovieSeat movieSeat) {
        return movieSeatRepository.save(movieSeat);
    }
}
