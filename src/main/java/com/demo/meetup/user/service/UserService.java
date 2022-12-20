package com.demo.meetup.user.service;

import org.springframework.stereotype.Service;

import com.demo.meetup.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;
    
}
