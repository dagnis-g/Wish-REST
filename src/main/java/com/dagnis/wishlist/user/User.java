package com.dagnis.wishlist.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {

    @NotNull
    private UserType type;
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

}
