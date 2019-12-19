package com.lannstark.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.lannstark.domain.user.QUser.*;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public UserCollection getUserCollection() {
        return new UserCollection(queryFactory.selectFrom(user).fetch());
    }

}
