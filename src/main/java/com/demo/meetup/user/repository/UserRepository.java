package com.demo.meetup.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.meetup.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email); 

    Optional<User> findById(Integer id);

}
