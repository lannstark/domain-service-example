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
        return userRepository.findUserByType(UserType.VIP).stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Integer levelUpUser(long userId) {
        // 1. 유저를 가져온다
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("존재하지 않는 유저(id: %s)입니다", userId)));

        // 2. 정해진 로직에 따라 유저를 레벨업 할지 말지 결정한다.
        if (user.getType().equals(UserType.VIP)) {
            user.setLevel(user.getLevel() + 1);
        } else if (user.getType().equals(UserType.NORMAL)) {
            if (user.getExp() > 1000) {
                user.setLevel(user.getLevel() + 1);
            }
        }

        // save가 없는데 dirty check 덕분에 저장이 되죠 (TEST 코드가 검증해줌)
        // 3. 바뀐 유저의 레벨을 반환한다
        return user.getLevel();
    }

}
