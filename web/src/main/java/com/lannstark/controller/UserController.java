package com.lannstark.controller;

import com.lannstark.dto.UserDto;
import com.lannstark.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 요구사항 2가지
 * 1) VIP 유저 목록 가져오기 -> 어드민 유저만 사용가능
 *    VIP 유저 가져오기가 사실은 복잡한 비즈니스적인 요구 사항이라 생각해주세요
 * 2) 레벨업 -> 실제 유저에게 호출되는 API
 *    VIP유저는 무조건 레벨업, 일반 유저는 경험치가 있어야 레벨업, 블락 유저는 무조건 불가
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/admin/user/vip")
    public List<UserDto> getVipUsers() {
        return userService.getVipUsers();
    }

    @PutMapping("/level")
    public Integer levelUpUser(@RequestParam Long userId) {
        return userService.levelUpUser(userId);
    }

}
