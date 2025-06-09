package com.mapping.entities;

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
public class UserEntity {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "userId"),
    inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<MovieSeat> movieSeats;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName) && Objects.equals(movieSeats, that.movieSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, movieSeats);
    }
}
