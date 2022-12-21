package com.demo.meetup.user.controller;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.meetup.GenericControllerTest;
import com.demo.meetup.user.exception.UserNotFoundException;
import com.demo.meetup.user.model.User;
import com.demo.meetup.user.repository.UserRepository;

public class UserControllerTest extends GenericControllerTest {

    private final String URL = "/api/v1/users/";
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repository;

    @Test
    @DisplayName("SHOULD return status code ok and user without password WHEN find user by id")
    public void findByIdTest_valid() throws Exception {

        var user = User.builder()
            .id(1)
            .name("teste1")
            .email("teste1@teste.com")
            .password("teste")
            .build();
            
        Mockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));        
        mockMvc.perform(MockMvcRequestBuilders.get(URL + user.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.password").doesNotExist());

    }

    @Test
    @DisplayName("SHOULD return status code not found WHEN not find user by id")
    public void findByIdTest_invalid() throws Exception {
            
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());   

        mockMvc.perform(MockMvcRequestBuilders.get(URL + 1))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.jsonPath("$.path").value(URL + "1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(getMessage(UserNotFoundException.MESSAGE_KEY)));

    }

    @Test
    @DisplayName("SHOULD return status code no contente WHEN delete user by valid id")
    public void deleteById_valid() throws Exception {

        var userId = 1;

        Mockito.when(repository.existsById(userId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + userId))
            .andExpect(MockMvcResultMatchers.status().isNoContent())
            .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());
            
    }

    @Test
    @DisplayName("SHOULD return status code not found WHEN delete user by id")
    public void deleteById_invalind() throws Exception {
        var userId = 1;
        Mockito.when(repository.existsById(userId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + userId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.path").value(URL + userId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(UserNotFoundException.MESSAGE_KEY));
    }

}
