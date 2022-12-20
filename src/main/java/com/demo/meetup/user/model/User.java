package com.demo.meetup.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    
    @Id
    private int id;

    @NotBlank(message = "user.name.not-blank")
    @Size(max = 255, message = "user.name.too-big")
    private String name;

    @NotBlank(message = "user.email.not-blank")
    @Email(message = "user.email.invalid")
    @Size(max = 255, message = "user.email.too-big")
    private String email;

    @NotBlank(message = "user.password.not-blank")
    @Size(max = 255, message = "user.password.too-big")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

}
