package com.lannstark.domain.user;

import com.lannstark.domain.types.UserType;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserCollection {

    private final List<User> users;

    public List<User> getVipUsers() {
        return users.stream()
                .filter(user -> user.hasType(UserType.VIP))
                .collect(Collectors.toList());
    }

}
