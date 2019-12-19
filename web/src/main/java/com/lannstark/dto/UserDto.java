package com.lannstark.dto;

import com.lannstark.domain.user.User;

public class UserDto {

    private final String name;
    private final int level;

    private UserDto(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static UserDto of(User user) {
        return new UserDto(user.getName(), user.getLevel());
    }

}
