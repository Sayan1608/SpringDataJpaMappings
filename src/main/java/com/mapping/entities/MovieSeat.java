package com.mapping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class MovieSeat {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isBooked;
    private String movieName;
    @Version
    private Long version;
    @ManyToMany(mappedBy = "movieSeats")
    @JsonIgnore
    private Set<UserEntity> audiences;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieSeat movieSeat)) return false;
        return isBooked == movieSeat.isBooked && Objects.equals(id, movieSeat.id) && Objects.equals(movieName, movieSeat.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isBooked, movieName);
    }
}
