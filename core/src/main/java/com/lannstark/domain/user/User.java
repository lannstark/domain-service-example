package com.lannstark.domain.user;

import com.lannstark.domain.types.UserType;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class User {

    private static final int LEVEL_UP_THRESHOLD = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private long exp;

    private int level;

    @Enumerated(value = EnumType.STRING)
    private UserType type;

    // 빌더를 그리 좋아하지는 않지만 우선 썼습니다
    @Builder
    public User(String name, long exp, int level, UserType type) {
        this.name = name;
        this.exp = exp;
        this.level = level;
        this.type = type;
    }

    public void levelUpUser() {
        if (canLevelUp()) {
            level++;
        }
    }

    private boolean canLevelUp() {
        if (type.equals(UserType.VIP)) {
            return true;
        }

        if (type.equals(UserType.NORMAL)) {
            return exp > LEVEL_UP_THRESHOLD;
        }

        return false;
    }

    boolean hasType(UserType type) {
        return this.type.equals(type);
    }

}
