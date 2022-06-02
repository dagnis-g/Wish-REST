package com.dagnis.wishlist.user;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Users {

    @NotNull
    private List<@Valid User> users;

}
