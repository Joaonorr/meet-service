package com.demo.meetup.user.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.meetup.user.model.User;
import com.demo.meetup.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {
    
    private final UserService service;

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody User user) {
        var saved = service.save(user);
        var path = "/api/v1/users" + saved.getId();
        var location = UriComponentsBuilder.fromPath(path).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
