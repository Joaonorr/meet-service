package com.demo.meetup.user.service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.demo.meetup.user.exception.UserNotFoundException;
import com.demo.meetup.user.model.User;
import com.demo.meetup.user.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    @DisplayName("SHOULD return user WHEN find user by id")
    public void FindByIdTest_valid() {

        var user = User.builder()
            .id(1)
            .name("teste1")
            .email("teste1@teste.com")
            .password("teste")
            .build();

        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));


        var userReturnerd = service.findById(user.getId());

        assertThat(userReturnerd).isEqualTo(user);
    }

    @Test
    @DisplayName("SHOULD throw UserNotFoundException WHEN not find user by id")
    public void FindByIdTest_invalid_notFound() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.findById(1));

    }
}
