package com.mapping.controllers;

import com.mapping.entities.UserEntity;
import com.mapping.services.MovieSeatBookingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {
    private final MovieSeatBookingService movieSeatBookingService;

    public BookingController(MovieSeatBookingService movieSeatBookingService) {
        this.movieSeatBookingService = movieSeatBookingService;
    }

    @PutMapping(path = "/optimistic/{seatId}/{userId}")
    public UserEntity bookMovieSeatUsingOptimisticLock(@PathVariable Long seatId, @PathVariable Long userId){
       return movieSeatBookingService.bookSeatUsingOptimisticLock(seatId,userId);
    }
}
