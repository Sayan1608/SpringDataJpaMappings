package com.mapping.services;

import com.mapping.entities.UserEntity;
import com.mapping.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createNewUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id : " + userId));
    }
}
