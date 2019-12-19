package com.lannstark.domain.user;

import com.lannstark.domain.types.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    List<User> findUserByType(UserType type);

}
