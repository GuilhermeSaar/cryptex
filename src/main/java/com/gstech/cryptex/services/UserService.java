package com.gstech.cryptex.services;

import com.gstech.cryptex.model.User;
import com.gstech.cryptex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User findById (Long id) {

        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("user not found")
        ));

        return user.get();
    }


}
