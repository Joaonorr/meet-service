package com.demo.meetup.user.service;

import org.springframework.stereotype.Service;

import com.demo.meetup.user.exception.UserNotFoundException;
import com.demo.meetup.user.model.User;
import com.demo.meetup.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;

    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }
}
