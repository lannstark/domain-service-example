package com.lannstark.service;

import com.lannstark.domain.types.UserType;
import com.lannstark.domain.user.User;
import com.lannstark.domain.user.UserRepository;
import com.lannstark.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 사실 이것도 꽤 깔끔하게 짠 편입니다
    // Dto가 Entity에 대한 의존성을 가지고 있게 만들고 Stream으로 매핑
    public List<UserDto> getVipUsers() {
        return userRepository.getUserCollection().getVipUsers()
                .stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Integer levelUpUser(long userId) {
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("존재하지 않는 유저(id: %s)입니다", userId)));
        user.levelUpUser();
        return user.getLevel();
    }

}
