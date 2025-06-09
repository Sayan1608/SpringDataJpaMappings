package com.mapping.controllers;

import com.mapping.entities.MovieSeat;
import com.mapping.services.MovieSeatBookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/seat")
public class MovieSeatBookingController {

    private final MovieSeatBookingService movieSeatBookingService;

    public MovieSeatBookingController(MovieSeatBookingService movieSeatBookingService) {
        this.movieSeatBookingService = movieSeatBookingService;
    }

    @PostMapping
    public MovieSeat createNewMovieSeat(@RequestBody MovieSeat movieSeat){
        return movieSeatBookingService.createNewMovieSeat(movieSeat);
    }


}
