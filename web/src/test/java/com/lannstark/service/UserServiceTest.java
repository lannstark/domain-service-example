package com.lannstark.service;

import com.lannstark.domain.types.UserType;
import com.lannstark.domain.user.User;
import com.lannstark.domain.user.UserRepository;
import com.lannstark.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void VIP_유저만_가져온다() {
        // given
        createUser(UserType.VIP);
        createUser(UserType.BLOCK);

        // when
        List<UserDto> result = userService.getVipUsers();

        // then
        assertThat(result).hasSize(1);
    }

    @Test
    void VIP유저는_바로_레벨업_한다() {
        // given
        createUser(UserType.VIP);

        // when
        userService.levelUpUser(getFirstUserId());

        // then
        assertThat(getFirstUser().getLevel()).isEqualTo(2);
    }

    // 이런식으로 여러 케이스에 대한 테스트 코드가 필요합니다...

    private void createUser(UserType type) {
        userRepository.save(User.builder()
                .name("아무나")
                .level(1)
                .type(type)
                .build());
    }

    private Long getFirstUserId() {
        return getFirstUser().getId();
    }

    private User getFirstUser() {
        return userRepository.findAll().get(0);
    }

}
