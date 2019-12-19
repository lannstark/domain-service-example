package com.lannstark.domain.user;

import com.lannstark.domain.types.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void 유저가_저장된다() {
        // give
        String userName = "아무나";
        User user = User.builder()
                .name(userName)
                .exp(0)
                .level(0)
                .type(UserType.NORMAL)
                .build();

        // when
        userRepository.save(user);

        // then
        assertThat(userRepository.findAll().get(0).getName()).isEqualTo(userName);
    }

}
