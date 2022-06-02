package com.dagnis.wishlist.user;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    public String returnNamesFromJson(Users users) {
        return users.getUsers().stream()
                .map(User::getName)
                .collect(Collectors.joining(", "));
    }
}
