package com.bibro.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    @Size(min = 5, max = 35, message = "Surname must be 2-35 characters long.")
    private String username;
    @Size(min = 5, max = 35, message = "Surname must be 2-35 characters long.")
    private String password;
    @Email(message = "Enter a valid email address.")
    private String email;
}
